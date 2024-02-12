/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 8
 */

package com.mich.gwan.shallom.service;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An abstract class representing an executor service for AsyncTask.
 *
 * @param <Params>   The type of parameters sent to the task upon execution.
 * @param <Progress> The type of progress units published during the background computation.
 * @param <Result>   The type of result of the background computation.
 */
public abstract class AsyncTaskExecutorService< Params, Progress, Result > {

    private final ExecutorService executor;
    private Handler handler;

    /**
     * Constructs a new AsyncTaskExecutorService.
     */
    protected AsyncTaskExecutorService() {
        executor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    /**
     * Gets the ExecutorService associated with this AsyncTaskExecutorService.
     *
     * @return The ExecutorService instance.
     */
    public ExecutorService getExecutor() {
        return executor;
    }

    /**
     * Gets the Handler associated with this AsyncTaskExecutorService. If the handler is null, it creates a new one.
     *
     * @return The Handler instance.
     */
    public Handler getHandler() {
        if (handler == null) {
            synchronized(AsyncTaskExecutorService.class) {
                handler = new Handler(Looper.getMainLooper());
            }
        }
        return handler;
    }

    /**
     * Method invoked on the UI thread before the background task is executed.
     */
    protected void onPreExecute() {
        // Override this method whereever you want to perform task before background execution get started
    }

    /**
     * Method that must be overridden to perform background computation.
     *
     * @param params The parameters for the background computation.
     * @return The result of the computation.
     */
    protected abstract Result doInBackground(Params params);

    /**
     * Method invoked on the UI thread after the background computation finishes.
     *
     * @param result The result of the background computation.
     */
    protected abstract void onPostExecute(Result result);

    /**
     * Method invoked on the UI thread to update progress during the background computation.
     * <p>
     *     Override this method whenever you want update a progress result
     * </p>
     * @param value The progress value to be updated.
     */
    protected void onProgressUpdate(@NotNull Progress value) {

    }

    /**
     * Pushes progress updates to the UI thread.
     *
     * @param value The progress value to be updated.
     */
    public void publishProgress(@NotNull Progress value) {
        getHandler().post(() -> onProgressUpdate(value));
    }

    /**
     * Executes the AsyncTaskExecutorService with no parameters.
     */
    public void execute() {
        execute(null);
    }

    /**
     * Executes the AsyncTaskExecutorService with the given parameters.
     *
     * @param params The parameters for the task.
     */
    public void execute(Params params) {
        getHandler().post(() -> {
            onPreExecute();
            executor.execute(() -> {
                Result result = doInBackground(params);
                getHandler().post(() -> onPostExecute(result));
            });
        });
    }

    /**
     * Shuts down the executor service.
     */
    public void shutDown() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    /**
     * Checks if the executor service is cancelled.
     *
     * @return True if the executor service is cancelled, otherwise false.
     */
    public boolean isCancelled() {
        return executor == null || executor.isTerminated() || executor.isShutdown();
    }
}