/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 23
 */

package com.mich.gwan.shallom.activity.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivitySignupLocalityBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SignUpLocalityActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerRegion;
    private Spinner spinnerCounty;
    private Spinner spinnerSubCounty;
    private Spinner spinnerVillage;
    private Spinner spinnerGroup;
    private Spinner spinnerDesignation;

    private TextInputLayout textInputLayoutImage;
    private TextInputLayout textInputLayoutRegion;
    private TextInputLayout textInputLayoutCounty;
    private TextInputLayout textInputLayoutSubCounty;
    private TextInputLayout textInputLayoutVillage;
    private TextInputLayout textInputLayoutGroup;
    private TextInputLayout textInputLayoutDesignation;

    private AppCompatButton buttonPrevious;
    private AppCompatButton buttonNext;

    private SignUpProfileActivity signUpProfileActivity;
    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    public User user;
    private ActivitySignupLocalityBinding binding;

    private Map<String, List<String>> countiesMap;
    private Map<String, List<String>> subCountiesMap;
    private Map<String, List<String>> villageMap;

    private List<String> subCounties;
    private List<String> villages;
    private List<String> groups;
    private List<String> designation;
    private List<String> regions;
    List<String> counties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupLocalityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initializes views, listeners, and objects using helper methods
        initViews();
        spinnerData();
        initListeners();
        initObjects();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);
    }

    private void spinnerData() {
        regions = new ArrayList<>();
        // Ad items to Region list
        regions.add("Select Region");
        regions.add("NAIROBI REGION");
        regions.add("COAST REGION");
        regions.add("MURANG'A REGION");
        regions.add("ELDORET REGION");
        regions.add("NAKURU REGION");
        regions.add("EMBU REGION");
        regions.add("KIRINYAGA REGION");
        regions.add("NAROK REGION");
        regions.add("KISUMU REGION");
        regions.add("TURKANA REGION");
        regions.add("NORTH EASTERN REGION");

        List<String> nairobiRegion = new ArrayList<>();
        nairobiRegion.add("Select County");
        nairobiRegion.add("016 Machakos");
        nairobiRegion.add("017 Makueni");
        nairobiRegion.add("022 Kiambu");
        nairobiRegion.add("047 Nairobi City");

        List<String> coastRegion = new ArrayList<>();
        coastRegion.add("Select County");
        coastRegion.add("001 Mombasa");
        coastRegion.add("002 Kwale");
        coastRegion.add("003 Kilifi");
        coastRegion.add("004 Tana River");
        coastRegion.add("005 Lamu");
        coastRegion.add("006 Taita Taveta");

        List<String> northEasternRegion = new ArrayList<>();
        northEasternRegion.add("Select County");
        northEasternRegion.add("007 Garissa");
        northEasternRegion.add("008 Wajir");
        northEasternRegion.add("009 Mandera");
        northEasternRegion.add("010 Marsabit");

        List<String> murangaCounties = new ArrayList<>();
        murangaCounties.add("Select County");
        murangaCounties.add("019 Nyeri");
        murangaCounties.add("021 Murang'a");

        List<String> eldoretRegion = new ArrayList<>();
        eldoretRegion.add("Select County");
        eldoretRegion.add("026 Trans Nzoia");
        eldoretRegion.add("027 Uasin Gishu");
        eldoretRegion.add("028 Elgeyo Marakwet");
        eldoretRegion.add("029 Nandi");
        eldoretRegion.add("030 Baringo");
        eldoretRegion.add("036 Bomet");
        eldoretRegion.add("037 Kakamega");
        eldoretRegion.add("038 Vihiga");
        eldoretRegion.add("039 Bungoma");
        eldoretRegion.add("040 Busia");

        List<String> nakuruRegion = new ArrayList<>();
        nakuruRegion.add("Select County");
        nakuruRegion.add("018 Nyandarua");
        nakuruRegion.add("031 Laikipia");
        nakuruRegion.add("032 Nakuru");

        List<String> kirinyagaRegion = new ArrayList<>();
        kirinyagaRegion.add("Select County");
        kirinyagaRegion.add("020 Kirinyaga");

        List<String> turkanaRegion = new ArrayList<>();
        turkanaRegion.add("Select County");
        turkanaRegion.add("023 Turkana");
        turkanaRegion.add("024 West Pokot");
        turkanaRegion.add("025 Samburu");

        List<String> embuRegion = new ArrayList<>();
        embuRegion.add("Select County");
        embuRegion.add("011 Isiolo");
        embuRegion.add("012 Meru");
        embuRegion.add("013 Tharaka-Nithi");
        embuRegion.add("014 Embu");
        embuRegion.add("015 Kitui");

        List<String> narokRegion = new ArrayList<>();
        narokRegion.add("Select County");
        narokRegion.add("033 Narok");
        narokRegion.add("034 Kajiado");
        narokRegion.add("035 Kericho");

        List<String> kisumuRegion = new ArrayList<>();
        kisumuRegion.add("Select County");
        kisumuRegion.add("041 Siaya");
        kisumuRegion.add("042 Kisumu");
        kisumuRegion.add("043 Homa Bay");
        kisumuRegion.add("044 Migori");
        kisumuRegion.add("045 Kisii");
        kisumuRegion.add("046 Nyamira");

        List<String> selectRegion = new ArrayList<>();
        selectRegion.add("Select County");

        List<String> nairobiSubCounties = new ArrayList<>();
        nairobiSubCounties.add("Select Sub-County");
        nairobiSubCounties.add("Dagoretti North");
        nairobiSubCounties.add("Dagoretti South");
        nairobiSubCounties.add("Embakasi Central");
        nairobiSubCounties.add("Embakasi East");
        nairobiSubCounties.add("Embakasi North");
        nairobiSubCounties.add("Embakasi South");
        nairobiSubCounties.add("Embakasi West");
        nairobiSubCounties.add("Kamukunji");
        nairobiSubCounties.add("Kasarani");
        nairobiSubCounties.add("Kibra");
        nairobiSubCounties.add("Lang’ata");
        nairobiSubCounties.add("Makadara");
        nairobiSubCounties.add("Mathare");
        nairobiSubCounties.add("Roysambu");
        nairobiSubCounties.add("Ruaraka");
        nairobiSubCounties.add("Starehe");
        nairobiSubCounties.add("Westlands");

        List<String> mombasaSubCounties = new ArrayList<>();
        mombasaSubCounties.add("Select Sub-County");
        mombasaSubCounties.add("Changamwe");
        mombasaSubCounties.add("Jomvu");
        mombasaSubCounties.add("Kisauni");
        mombasaSubCounties.add("Likoni");
        mombasaSubCounties.add("Mvita");
        mombasaSubCounties.add("Nyali");


        List<String> kwaleSubCounties = new ArrayList<>();
        kwaleSubCounties.add("Select Sub-County");
        kwaleSubCounties.add("Kinango");
        kwaleSubCounties.add("Lunga Lunga");
        kwaleSubCounties.add("Msambweni");
        kwaleSubCounties.add("Matuga");

        List<String> kilifiSubCounties = new ArrayList<>();
        kilifiSubCounties.add("Select Sub-County");
        kilifiSubCounties.add("Ganze");
        kilifiSubCounties.add("Kaloleni");
        kilifiSubCounties.add("Kilifi North");
        kilifiSubCounties.add("Kilifi South");
        kilifiSubCounties.add("Magarini");
        kilifiSubCounties.add("Malindi");
        kilifiSubCounties.add("Rabai");

        List<String> tanaRiverSubCounties = new ArrayList<>();
        tanaRiverSubCounties.add("Select Sub-County");
        tanaRiverSubCounties.add("Bura");
        tanaRiverSubCounties.add("Galole");
        tanaRiverSubCounties.add("Garsen");

        List<String> lamuSubCounties = new ArrayList<>();
        lamuSubCounties.add("Select Sub-County");
        lamuSubCounties.add("Lamu East");
        lamuSubCounties.add("Lamu West");

        List<String> taitaTavetaSubCounties = new ArrayList<>();
        taitaTavetaSubCounties.add("Select Sub-County");
        taitaTavetaSubCounties.add("Mwatate");
        taitaTavetaSubCounties.add("Taveta");
        taitaTavetaSubCounties.add("Voi");
        taitaTavetaSubCounties.add("Wundanyi");

        List<String> garisaSubCounties = new ArrayList<>();
        garisaSubCounties.add("Select Sub-County");
        garisaSubCounties.add("Daadab");
        garisaSubCounties.add("Fafi");
        garisaSubCounties.add("Garissa Township");
        garisaSubCounties.add("Hulugho");
        garisaSubCounties.add("Ijara");
        garisaSubCounties.add("Lagdera");
        garisaSubCounties.add("Balambala");

        List<String> wajirSubCounties = new ArrayList<>();
        wajirSubCounties.add("Select Sub-County");
        wajirSubCounties.add("Eldas");
        wajirSubCounties.add("Tarbaj");
        wajirSubCounties.add("Wajir East");
        wajirSubCounties.add("Wajir North");
        wajirSubCounties.add("Wajir South");
        wajirSubCounties.add("Wajir West");

        List<String> manderaSubCounties = new ArrayList<>();
        manderaSubCounties.add("Select Sub-County");
        manderaSubCounties.add("Banissa");
        manderaSubCounties.add("Lafey");
        manderaSubCounties.add("Mandera East");
        manderaSubCounties.add("Mandera North");
        manderaSubCounties.add("Mandera South");
        manderaSubCounties.add("Mandera West");

        List<String> marsabitSubCounties = new ArrayList<>();
        marsabitSubCounties.add("Select Sub-County");
        marsabitSubCounties.add("Laisamis");
        marsabitSubCounties.add("Moyale");
        marsabitSubCounties.add("North Hor");
        marsabitSubCounties.add("Saku");

        List<String> isioloSubCounties = new ArrayList<>();
        isioloSubCounties.add("Select Sub-County");
        isioloSubCounties.add("Isiolo");
        isioloSubCounties.add("Merti");
        isioloSubCounties.add("Garbatulla");

        List<String> meruSubCounties = new ArrayList<>();
        meruSubCounties.add("Select Sub-County");
        meruSubCounties.add("Buuri");
        meruSubCounties.add("Igembe Central");
        meruSubCounties.add("Igembe North");
        meruSubCounties.add("Igembe South");
        meruSubCounties.add("Imenti Central");
        meruSubCounties.add("Imenti North");
        meruSubCounties.add("Imenti South");
        meruSubCounties.add("Tigania East");
        meruSubCounties.add("Tigania West");

        List<String> tharakaNithiSubCounties = new ArrayList<>();
        tharakaNithiSubCounties.add("Select Sub-County");
        tharakaNithiSubCounties.add("Tharaka North");
        tharakaNithiSubCounties.add("Tharaka South");
        tharakaNithiSubCounties.add("Chuka");
        tharakaNithiSubCounties.add("Igambango’mbe");
        tharakaNithiSubCounties.add("Maara");
        tharakaNithiSubCounties.add("Chiakariga and Muthambi");

        List<String> embuSubCounties = new ArrayList<>();
        embuSubCounties.add("Select Sub-County");
        embuSubCounties.add("Manyatta");
        embuSubCounties.add("Mbeere North");
        embuSubCounties.add("Mbeere South");
        embuSubCounties.add("Runyenjes");

        List<String> kituiSubCounties = new ArrayList<>();
        kituiSubCounties.add("Select Sub-County");
        kituiSubCounties.add("Kitui West");
        kituiSubCounties.add("Kitui Central");
        kituiSubCounties.add("Kitui Rural");
        kituiSubCounties.add("Kitui South");
        kituiSubCounties.add("Kitui East");
        kituiSubCounties.add("Mwingi North");
        kituiSubCounties.add("Mwingi West");
        kituiSubCounties.add("Mwingi Central");

        List<String> machakosSubCounties = new ArrayList<>();
        machakosSubCounties.add("Select Sub-County");
        machakosSubCounties.add("Kathiani");
        machakosSubCounties.add("Machakos Town");
        machakosSubCounties.add("Masinga");
        machakosSubCounties.add("Matungulu");
        machakosSubCounties.add("Mavoko");
        machakosSubCounties.add("Mwala");
        machakosSubCounties.add("Yatta");

        List<String> makueniSubCounties = new ArrayList<>();
        makueniSubCounties.add("Select Sub-County");
        makueniSubCounties.add("Kaiti");
        makueniSubCounties.add("Kibwezi West");
        makueniSubCounties.add("Kibwezi East");
        makueniSubCounties.add("Kilome");
        makueniSubCounties.add("Makueni");
        makueniSubCounties.add("Mbooni");

        List<String> nyadaruaSubCounties = new ArrayList<>();
        nyadaruaSubCounties.add("Select Sub-County");
        nyadaruaSubCounties.add("Kinangop");
        nyadaruaSubCounties.add("Kipipiri");
        nyadaruaSubCounties.add("Ndaragwa");
        nyadaruaSubCounties.add("Ol-Kalou");
        nyadaruaSubCounties.add("Ol Joro Orok");

        List<String> nyeriSubCounties = new ArrayList<>();
        nyeriSubCounties.add("Select Sub-County");
        nyeriSubCounties.add("Kieni East");
        nyeriSubCounties.add("Kieni West");
        nyeriSubCounties.add("Mathira East");
        nyeriSubCounties.add("Mathira West");
        nyeriSubCounties.add("Mukurweini");
        nyeriSubCounties.add("Nyeri Town");
        nyeriSubCounties.add("Othaya");
        nyeriSubCounties.add("Tetu");

        List<String> kirinyagaSubCounties = new ArrayList<>();
        kirinyagaSubCounties.add("Select Sub-County");
        kirinyagaSubCounties.add("Kirinyaga Central");
        kirinyagaSubCounties.add("Kirinyaga East");
        kirinyagaSubCounties.add("Kirinyaga West");
        kirinyagaSubCounties.add("Mwea East");
        kirinyagaSubCounties.add("Mwea West");

        List<String> murangaSubCounties = new ArrayList<>();
        murangaSubCounties.add("Select Sub-County");
        murangaSubCounties.add("Gatanga");
        murangaSubCounties.add("Kahuro");
        murangaSubCounties.add("Kandara");
        murangaSubCounties.add("Kangema");
        murangaSubCounties.add("Kigumo");
        murangaSubCounties.add("Kiharu");
        murangaSubCounties.add("Mathioya");
        murangaSubCounties.add("Murang’a South");

        List<String> kiambuSubCounties = new ArrayList<>();
        kiambuSubCounties.add("Select Sub-County");
        kiambuSubCounties.add("Gatundu North");
        kiambuSubCounties.add("Gatundu South");
        kiambuSubCounties.add("Githunguri");
        kiambuSubCounties.add("Juja");
        kiambuSubCounties.add("Kabete");
        kiambuSubCounties.add("Kiambaa");
        kiambuSubCounties.add("Kiambu");
        kiambuSubCounties.add("Kikuyu");
        kiambuSubCounties.add("Limuru");
        kiambuSubCounties.add("Ruiru");
        kiambuSubCounties.add("Thika Town");
        kiambuSubCounties.add("Lari");

        List<String> turkanaSubCounties = new ArrayList<>();
        turkanaSubCounties.add("Select Sub-County");
        turkanaSubCounties.add("Loima");
        turkanaSubCounties.add("Turkana Central");
        turkanaSubCounties.add("Turkana East");
        turkanaSubCounties.add("Turkana North");
        turkanaSubCounties.add("Turkana South");

        List<String> westPokotSubCounties = new ArrayList<>();
        westPokotSubCounties.add("Select Sub-County");
        westPokotSubCounties.add("Central Pokot");
        westPokotSubCounties.add("North Pokot");
        westPokotSubCounties.add("Pokot South");
        westPokotSubCounties.add("West Pokot");

        List<String> samburuSubCounties = new ArrayList<>();
        samburuSubCounties.add("Select Sub-County");
        samburuSubCounties.add("Samburu East");
        samburuSubCounties.add("Samburu North");
        samburuSubCounties.add("Samburu West");

        List<String> transNzoiaSubCounties = new ArrayList<>();
        transNzoiaSubCounties.add("Select Sub-County");
        transNzoiaSubCounties.add("Cherangany");
        transNzoiaSubCounties.add("Endebess");
        transNzoiaSubCounties.add("Kiminini");
        transNzoiaSubCounties.add("Kwanza");
        transNzoiaSubCounties.add("Saboti");

        List<String> uasinGishuSubCounties = new ArrayList<>();
        uasinGishuSubCounties.add("Select Sub-County");
        uasinGishuSubCounties.add("Ainabkoi");
        uasinGishuSubCounties.add("Kapseret");
        uasinGishuSubCounties.add("Kesses");
        uasinGishuSubCounties.add("Moiben");
        uasinGishuSubCounties.add("Soy");
        uasinGishuSubCounties.add("Turbo");

        List<String> elgeyoMarakwetSubCounties = new ArrayList<>();
        elgeyoMarakwetSubCounties.add("Select Sub-County");
        elgeyoMarakwetSubCounties.add("Keiyo North");
        elgeyoMarakwetSubCounties.add("Keiyo South");
        elgeyoMarakwetSubCounties.add("Marakwet East");
        elgeyoMarakwetSubCounties.add("Marakwet West");

        List<String> nandiSubCounties = new ArrayList<>();
        nandiSubCounties.add("Select Sub-County");
        nandiSubCounties.add("Aldai");
        nandiSubCounties.add("Chesumei");
        nandiSubCounties.add("Emgwen");
        nandiSubCounties.add("Mosop");
        nandiSubCounties.add("Nandi Hills");
        nandiSubCounties.add("Tindiret");

        List<String> baringoSubCounties = new ArrayList<>();
        baringoSubCounties.add("Select Sub-County");
        baringoSubCounties.add("Baringo Central");
        baringoSubCounties.add("Baringo North");
        baringoSubCounties.add("Baringo South");
        baringoSubCounties.add("Eldama Ravine");
        baringoSubCounties.add("Mogotio");
        baringoSubCounties.add("Tiaty");

        List<String> laikipiaSubCounties = new ArrayList<>();
        laikipiaSubCounties.add("Select Sub-County");
        laikipiaSubCounties.add("Laikipia Central");
        laikipiaSubCounties.add("Laikipia East");
        laikipiaSubCounties.add("Laikipia North");
        laikipiaSubCounties.add("Laikipia West");
        laikipiaSubCounties.add("Nyahururu");

        List<String> nakuruSubCounties = new ArrayList<>();
        nakuruSubCounties.add("Select Sub-County");
        nakuruSubCounties.add("Bahati");
        nakuruSubCounties.add("Gilgil");
        nakuruSubCounties.add("Kuresoi North");
        nakuruSubCounties.add("Kuresoi South");
        nakuruSubCounties.add("Molo");
        nakuruSubCounties.add("Naivasha");
        nakuruSubCounties.add("Nakuru Town East");
        nakuruSubCounties.add("Nakuru Town West");
        nakuruSubCounties.add("Njoro");
        nakuruSubCounties.add("Rongai");
        nakuruSubCounties.add("Subukia");

        List<String> narokSubCounties = new ArrayList<>();
        narokSubCounties.add("Select Sub-County");
        narokSubCounties.add("Narok East");
        narokSubCounties.add("Narok North");
        narokSubCounties.add("Narok South");
        narokSubCounties.add("Narok West");
        narokSubCounties.add("Transmara East");
        narokSubCounties.add("Transmara West");

        List<String> kajiadoSubCounties = new ArrayList<>();
        kajiadoSubCounties.add("Select Sub-County");
        kajiadoSubCounties.add("sinya");
        kajiadoSubCounties.add("ajiado Central");
        kajiadoSubCounties.add("ajiado North");
        kajiadoSubCounties.add("oitokitok");
        kajiadoSubCounties.add("ashuuru");

        List<String> kerichoSubCounties = new ArrayList<>();
        kerichoSubCounties.add("Select Sub-County");
        kerichoSubCounties.add("Ainamoi");
        kerichoSubCounties.add("Belgut");
        kerichoSubCounties.add("Bureti");
        kerichoSubCounties.add("Kipkelion East");
        kerichoSubCounties.add("Kipkelion West");
        kerichoSubCounties.add("Soin/Sigowet");

        List<String> bometSubCounties = new ArrayList<>();
        bometSubCounties.add("Select Sub-County");
        bometSubCounties.add("Bomet Central");
        bometSubCounties.add("Bomet East");
        bometSubCounties.add("Chepalungu");
        bometSubCounties.add("Konoin");
        bometSubCounties.add("Sotik");

        List<String> kakamegaSubCounties = new ArrayList<>();
        kakamegaSubCounties.add("Select Sub-County");
        kakamegaSubCounties.add("Butere");
        kakamegaSubCounties.add("Kakamega Central");
        kakamegaSubCounties.add("Kakamega East");
        kakamegaSubCounties.add("Kakamega North");
        kakamegaSubCounties.add("Kakamega South");
        kakamegaSubCounties.add("Khwisero");
        kakamegaSubCounties.add("Lugari");
        kakamegaSubCounties.add("Lukuyani");
        kakamegaSubCounties.add("Lurambi");
        kakamegaSubCounties.add("Matete");
        kakamegaSubCounties.add("Mumias");
        kakamegaSubCounties.add("Mutungu");
        kakamegaSubCounties.add("Navakholo");

        List<String> vihigaSubCounties = new ArrayList<>();
        vihigaSubCounties.add("Select Sub-County");
        vihigaSubCounties.add("Emuhaya");
        vihigaSubCounties.add("Hamisi");
        vihigaSubCounties.add("Luanda");
        vihigaSubCounties.add("Sabatia");
        vihigaSubCounties.add("Vihiga");

        List<String> bungomaSubCounties = new ArrayList<>();
        bungomaSubCounties.add("Select Sub-County");
        bungomaSubCounties.add("Bumula");
        bungomaSubCounties.add("Kabuchai");
        bungomaSubCounties.add("Kanduyi");
        bungomaSubCounties.add("Kimilil");
        bungomaSubCounties.add("Mt Elgon");
        bungomaSubCounties.add("Sirisia");
        bungomaSubCounties.add("Tongaren");
        bungomaSubCounties.add("Webuye East");
        bungomaSubCounties.add("Webuye West");

        List<String> busiaSubCounties = new ArrayList<>();
        busiaSubCounties.add("Select Sub-County");
        busiaSubCounties.add("Budalangi");
        busiaSubCounties.add("Butula");
        busiaSubCounties.add("Funyula");
        busiaSubCounties.add("Nambele");
        busiaSubCounties.add("Teso North");
        busiaSubCounties.add("Teso South");

        List<String> siayaSubCounties = new ArrayList<>();
        siayaSubCounties.add("Select Sub-County");
        siayaSubCounties.add("Alego Usonga");
        siayaSubCounties.add("Bondo");
        siayaSubCounties.add("Gem");
        siayaSubCounties.add("Rarieda");
        siayaSubCounties.add("Ugenya");
        siayaSubCounties.add("Unguja");

        List<String> kisumuSubCounties = new ArrayList<>();
        kisumuSubCounties.add("Select Sub-County");
        kisumuSubCounties.add("Kisumu Central");
        kisumuSubCounties.add("Kisumu East");
        kisumuSubCounties.add("Kisumu West");
        kisumuSubCounties.add("Muhoroni");
        kisumuSubCounties.add("Nyakach");
        kisumuSubCounties.add("Nyando");
        kisumuSubCounties.add("Seme");

        List<String> homaBaySubCounties = new ArrayList<>();
        homaBaySubCounties.add("Select Sub-County");
        homaBaySubCounties.add("Homabay Town");
        homaBaySubCounties.add("Kabondo");
        homaBaySubCounties.add("Karachwonyo");
        homaBaySubCounties.add("Kasipul");
        homaBaySubCounties.add("Mbita");
        homaBaySubCounties.add("Ndhiwa");
        homaBaySubCounties.add("Rangwe");
        homaBaySubCounties.add("Suba");

        List<String> migoriSubCounties = new ArrayList<>();
        migoriSubCounties.add("Select Sub-County");
        migoriSubCounties.add("Awendo");
        migoriSubCounties.add("Kuria East");
        migoriSubCounties.add("Kuria West");
        migoriSubCounties.add("Mabera");
        migoriSubCounties.add("Ntimaru");
        migoriSubCounties.add("Rongo");
        migoriSubCounties.add("Suna East");
        migoriSubCounties.add("Suna West");
        migoriSubCounties.add("Uriri");

        List<String> kisiiSubCounties = new ArrayList<>();
        kisiiSubCounties.add("Select Sub-County");
        kisiiSubCounties.add("Kitutu Chache North");
        kisiiSubCounties.add("Kitutu Chache");
        kisiiSubCounties.add("Nyaribari Masaba");
        kisiiSubCounties.add("Nyaribari Chache");
        kisiiSubCounties.add("Bomachoge Borabu");
        kisiiSubCounties.add("Bomachoge Chache");
        kisiiSubCounties.add("Bobasi");
        kisiiSubCounties.add("South Mugirango");
        kisiiSubCounties.add("Bonchari");

        List<String> nyamiraSubCounties = new ArrayList<>();
        nyamiraSubCounties.add("Select Sub-County");
        nyamiraSubCounties.add("Borabu");
        nyamiraSubCounties.add("Manga");
        nyamiraSubCounties.add("Masaba North");
        nyamiraSubCounties.add("Nyamira North");
        nyamiraSubCounties.add("Nyamira South");

        List<String> selectCounty = new ArrayList<>();
        selectCounty.add("Select Sub-County");

        /**
         * Villages arraylists
         */

        List<String> nyaliVillages = new ArrayList<>();
        nyaliVillages.add("Select Village");
        nyaliVillages.add("KISIMANI");

        List<String> jomvuVillages = new ArrayList<>();
        jomvuVillages.add("Select Village");
        jomvuVillages.add("CHANGAMWE");

        List<String> msambeniVillages = new ArrayList<>();
        msambeniVillages.add("Select Village");
        msambeniVillages.add("SHIMBA HILLS");
        msambeniVillages.add("UKUNDA");

        List<String> kilifiSouthVillages = new ArrayList<>();
        kilifiSouthVillages.add("Select Village");
        kilifiSouthVillages.add("KILIFI");
        kilifiSouthVillages.add("MSABAHA");
        kilifiSouthVillages.add("GEDE");
        kilifiSouthVillages.add("DIDA");

        List<String> ganzeVillages = new ArrayList<>();
        ganzeVillages.add("Select Village");
        ganzeVillages.add("SHARIANI");

        List<String> kaloleniVillages = new ArrayList<>();
        kaloleniVillages.add("Select Village");
        kaloleniVillages.add("MKAPUNI");

        List<String> roysambuVillages = new ArrayList<>();
        roysambuVillages.add("Select Village");
        roysambuVillages.add("MARURUI");

        List<String> kamukunjiVillages = new ArrayList<>();
        kamukunjiVillages.add("Select Village");
        kamukunjiVillages.add("EASTLEIGH - HQ");

        List<String> embakasiNorthVillages = new ArrayList<>();
        embakasiNorthVillages.add("Select Village");
        embakasiNorthVillages.add("RUAI");

        List<String> kabeteVillages = new ArrayList<>();
        kabeteVillages.add("Select Village");
        kabeteVillages.add("GUDOK");

        List<String> jujaVillages = new ArrayList<>();
        jujaVillages.add("Select Village");
        jujaVillages.add("JUJA");

        List<String> kiambuVillages = new ArrayList<>();
        kiambuVillages.add("Select Village");
        kiambuVillages.add("KIAMBU");

        List<String> ruiruVillages = new ArrayList<>();
        ruiruVillages.add("Select Village");
        ruiruVillages.add("RUIRU - BOOSTER");

        List<String> limuruVillages = new ArrayList<>();
        limuruVillages.add("Select Village");
        limuruVillages.add("LIMURU - TIEKUNU");

        List<String> thikaTownVillages = new ArrayList<>();
        thikaTownVillages.add("Select Village");
        thikaTownVillages.add("THIKA - KIGANJO");

        List<String> selectSubCounty = new ArrayList<>();
        selectSubCounty.add("Select Village");


        // Convert all to uppercase
        replaceAllWithUppercase(nairobiRegion);
        replaceAllWithUppercase(coastRegion);
        replaceAllWithUppercase(northEasternRegion);
        replaceAllWithUppercase(murangaCounties);
        replaceAllWithUppercase(eldoretRegion);
        replaceAllWithUppercase(nakuruRegion);
        replaceAllWithUppercase(kirinyagaRegion);
        replaceAllWithUppercase(turkanaRegion);
        replaceAllWithUppercase(embuRegion);
        replaceAllWithUppercase(narokRegion);
        replaceAllWithUppercase(kisumuRegion);
        replaceAllWithUppercase(nairobiSubCounties);
        replaceAllWithUppercase(mombasaSubCounties);
        replaceAllWithUppercase(kwaleSubCounties);
        replaceAllWithUppercase(kilifiSubCounties);
        replaceAllWithUppercase(tanaRiverSubCounties);
        replaceAllWithUppercase(lamuSubCounties);
        replaceAllWithUppercase(taitaTavetaSubCounties);
        replaceAllWithUppercase(garisaSubCounties);
        replaceAllWithUppercase(wajirSubCounties);
        replaceAllWithUppercase(manderaSubCounties);
        replaceAllWithUppercase(marsabitSubCounties);
        replaceAllWithUppercase(isioloSubCounties);
        replaceAllWithUppercase(meruSubCounties);
        replaceAllWithUppercase(tharakaNithiSubCounties);
        replaceAllWithUppercase(embuSubCounties);
        replaceAllWithUppercase(kituiSubCounties);
        replaceAllWithUppercase(machakosSubCounties);
        replaceAllWithUppercase(makueniSubCounties);
        replaceAllWithUppercase(nyadaruaSubCounties);
        replaceAllWithUppercase(nyeriSubCounties);
        replaceAllWithUppercase(kirinyagaSubCounties);
        replaceAllWithUppercase(murangaSubCounties);
        replaceAllWithUppercase(kiambuSubCounties);
        replaceAllWithUppercase(turkanaSubCounties);
        replaceAllWithUppercase(westPokotSubCounties);
        replaceAllWithUppercase(samburuSubCounties);
        replaceAllWithUppercase(transNzoiaSubCounties);
        replaceAllWithUppercase(uasinGishuSubCounties);
        replaceAllWithUppercase(elgeyoMarakwetSubCounties);
        replaceAllWithUppercase(nandiSubCounties);
        replaceAllWithUppercase(baringoSubCounties);
        replaceAllWithUppercase(laikipiaSubCounties);
        replaceAllWithUppercase(nakuruSubCounties);
        replaceAllWithUppercase(narokSubCounties);
        replaceAllWithUppercase(kajiadoSubCounties);
        replaceAllWithUppercase(kerichoSubCounties);
        replaceAllWithUppercase(bometSubCounties);
        replaceAllWithUppercase(kakamegaSubCounties);
        replaceAllWithUppercase(vihigaSubCounties);
        replaceAllWithUppercase(bungomaSubCounties);
        replaceAllWithUppercase(busiaSubCounties);
        replaceAllWithUppercase(siayaSubCounties);
        replaceAllWithUppercase(kisumuSubCounties);
        replaceAllWithUppercase(homaBaySubCounties);
        replaceAllWithUppercase(migoriSubCounties);
        replaceAllWithUppercase(kisiiSubCounties);
        replaceAllWithUppercase(nyamiraSubCounties);

        countiesMap = new HashMap<>();
        // put key (REGION) and value (COUNTY-LIST) to the countyMap
        countiesMap.put("SELECT REGION", selectRegion);
        countiesMap.put("NAIROBI REGION", nairobiRegion);
        countiesMap.put("COAST REGION", coastRegion);
        countiesMap.put("MURANG'A REGION", murangaCounties);
        countiesMap.put("ELDORET REGION", eldoretRegion);
        countiesMap.put("NAKURU REGION", nakuruRegion);
        countiesMap.put("EMBU REGION", embuRegion);
        countiesMap.put("KIRINYAGA REGION", kirinyagaRegion);
        countiesMap.put("NAROK REGION", narokRegion);
        countiesMap.put("KISUMU REGION", kisumuRegion);
        countiesMap.put("TURKANA REGION", turkanaRegion);
        countiesMap.put("NORTH EASTERN REGION", northEasternRegion);

        subCountiesMap = new HashMap<>();
        // put key (COUNTY) and value (SUB-COUNTY-LIST) to the subCountyMap
        subCountiesMap.put("SELECT COUNTY", selectCounty);
        subCountiesMap.put("001 Mombasa", mombasaSubCounties);
        subCountiesMap.put("002 Kwale", kwaleSubCounties);
        subCountiesMap.put("003 Kilifi", kilifiSubCounties);
        subCountiesMap.put("004 Tana River", tanaRiverSubCounties);
        subCountiesMap.put("005 Lamu", lamuSubCounties);
        subCountiesMap.put("006 Taita Taveta", taitaTavetaSubCounties);
        subCountiesMap.put("007 Garissa", garisaSubCounties);
        subCountiesMap.put("008 Wajir", wajirSubCounties);
        subCountiesMap.put("009 Mandera", manderaSubCounties);
        subCountiesMap.put("010 Marsabit", marsabitSubCounties);
        subCountiesMap.put("011 Isiolo", isioloSubCounties);
        subCountiesMap.put("012 Meru", meruSubCounties);
        subCountiesMap.put("013 Tharaka-Nithi", tharakaNithiSubCounties);
        subCountiesMap.put("014 Embu", embuSubCounties);
        subCountiesMap.put("015 Kitui", kituiSubCounties);
        subCountiesMap.put("016 Machakos", machakosSubCounties);
        subCountiesMap.put("017 Makueni", makueniSubCounties);
        subCountiesMap.put("018 Nyandarua", nyadaruaSubCounties);
        subCountiesMap.put("019 Nyeri", nyeriSubCounties);
        subCountiesMap.put("020 Kirinyaga", kirinyagaSubCounties);
        subCountiesMap.put("021 Murang'a", murangaSubCounties);
        subCountiesMap.put("022 Kiambu", kiambuSubCounties);
        subCountiesMap.put("023 Turkana", turkanaSubCounties);
        subCountiesMap.put("024 West Pokot", westPokotSubCounties);
        subCountiesMap.put("025 Samburu", samburuSubCounties);
        subCountiesMap.put("026 Trans Nzoia", transNzoiaSubCounties);
        subCountiesMap.put("027 Uasin Gishu", uasinGishuSubCounties);
        subCountiesMap.put("028 Elgeyo Marakwet", elgeyoMarakwetSubCounties);
        subCountiesMap.put("029 Nandi", nandiSubCounties);
        subCountiesMap.put("030 Baringo", baringoSubCounties);
        subCountiesMap.put("031 Laikipia", laikipiaSubCounties);
        subCountiesMap.put("032 Nakuru", nakuruSubCounties);
        subCountiesMap.put("033 Narok", narokSubCounties);
        subCountiesMap.put("034 Kajiado", kajiadoSubCounties);
        subCountiesMap.put("035 Kericho", kerichoSubCounties);
        subCountiesMap.put("036 Bomet", bometSubCounties);
        subCountiesMap.put("037 Kakamega", kakamegaSubCounties);
        subCountiesMap.put("038 Vihiga", vihigaSubCounties);
        subCountiesMap.put("039 Bungoma", bungomaSubCounties);
        subCountiesMap.put("040 Busia", busiaSubCounties);
        subCountiesMap.put("041 Siaya", siayaSubCounties);
        subCountiesMap.put("042 Kisumu", kisumuSubCounties);
        subCountiesMap.put("043 Homa Bay", homaBaySubCounties);
        subCountiesMap.put("044 Migori", migoriSubCounties);
        subCountiesMap.put("045 Kisii", kisiiSubCounties);
        subCountiesMap.put("046 Nyamira", nyamiraSubCounties);
        subCountiesMap.put("047 NAIROBI CITY", nairobiSubCounties);

        villageMap = new HashMap<>();
        // Put key (SUB-COUNTY) and value (VILLAGE-LIST) to villageMap
        villageMap.put("SELECT SUB-COUNTY", selectCounty);
        villageMap.put("NYALI", nyaliVillages);
        villageMap.put("JOMVU", jomvuVillages);
        villageMap.put("MSAMBWENI", msambeniVillages);
        villageMap.put("KILIFI SOUTH", kilifiSouthVillages);
        villageMap.put("GANZE", ganzeVillages);
        villageMap.put("KALOLENI", kaloleniVillages);
        villageMap.put("ROYSAMBU", roysambuVillages);
        villageMap.put("KAMKUNJI", kamukunjiVillages);
        villageMap.put("EMBAKASI NORTH", embakasiNorthVillages);
        villageMap.put("KABETE", kabeteVillages);
        villageMap.put("JUJA", jujaVillages);
        villageMap.put("KIAMBU", kiambuVillages);
        villageMap.put("RUIRU", ruiruVillages);
        villageMap.put("LIMURU", limuruVillages);
        villageMap.put("THIKA", thikaTownVillages);

        // Set all keys in the map to uppercase
        replaceAllKeysWithUppercase(subCountiesMap);

        // Set up ArrayAdapter for regions
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, regions);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(dataAdapter);

        // set up listener for regionSpinner
        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected region
                String selectedRegion = parent.getItemAtPosition(position).toString();

                // Update counties spinner based on the selected region
                updateCounties(selectedRegion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when nothing is selected
                // Log a message to help with debugging
                Log.e("OnItemSelectedListener", "No item selected in the region spinner");
            }
        });

        // Set up listener for countySpinner
        spinnerCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Update sub-counties based on the selected county
                updateSubCounties(counties.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        
        spinnerSubCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateVillages(subCounties.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        groups = new ArrayList<>();
        // Add items to Group list
        groups.add("Select Group");
        groups.add("COGYOK");
        groups.add("MEWAK");
        groups.add("COGMEA");
        groups.add("MEYO");
        groups.add("LEWA");

        designation = new ArrayList<>();
        // Add values to Designation list
        designation.add("Select Designation");
        designation.add("CHAIRPERSON");
        designation.add("OFFICE BEARER");
        designation.add("GOVERNOR");
        designation.add("DEPUTY GOVERNOR");
        designation.add("SECRETARY (DIST)");
        designation.add("SECRETARY (VILL)");
        designation.add("SECRETARY (NATL)");
        designation.add("TREASURER (NATL)");
        designation.add("TREASURER (DIST)");
        designation.add("TREASURER (VILL)");
        designation.add("PRESIDENT");
        designation.add("DEPUTY PRESIDENT");
        designation.add("OVERSEER");
        designation.add("DEPUTY OVERSEER");
        designation.add("PASTOR");
        designation.add("ELDER");
        designation.add("DEACON");
        designation.add("MEMBER");

        // Set up ArrayAdapter for groups
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(groupAdapter);

        // Set up ArrayAdapter for designation
        ArrayAdapter<String> designationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, designation);
        designationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesignation.setAdapter(designationAdapter);
    }

    private void updateCounties(String selectedRegion){
        // Get the counties for the selected region
        counties = countiesMap.get(selectedRegion);

        // Update county spinner
        ArrayAdapter<String> countyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, counties);
        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCounty.setAdapter(countyAdapter);
    }
    private void updateSubCounties(String selectedCounty) {
        // Get the sub-counties for the selected county
        subCounties = subCountiesMap.get(selectedCounty);

        // Update sub-county spinner
        ArrayAdapter<String> subCountyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subCounties);
        subCountyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubCounty.setAdapter(subCountyAdapter);
    }

    private void updateVillages(String selectedSubCounty){
        // Get the villages for the selected sub-county
        villages = villageMap.get(selectedSubCounty);

        // Set up ArrayAdapter for sub-counties
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, villages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVillage.setAdapter(adapter);
    }

    /**
     * Initializes the views for the user input fields, spinners, and buttons.
     * Also sets default values for some of the spinners.
     */
    private void initViews() {
        spinnerCounty = binding.spinnerCounty;
        spinnerRegion = binding.spinnerRegion;
        spinnerSubCounty = binding.spinnerSubCounty;
        spinnerVillage = binding.spinnerVillage;
        spinnerGroup = binding.spinnerGroup;
        spinnerDesignation = binding.spinnerDesignation;
        buttonPrevious = binding.buttonPrevious;
        buttonNext = binding.buttonNext;
        textInputLayoutImage = binding.textInputLayoutImage;
        textInputLayoutCounty = binding.textInputLayoutCounty;
        textInputLayoutRegion = binding.textInputLayoutRegion;
        textInputLayoutSubCounty = binding.textInputLayoutSubCounty;
        textInputLayoutVillage = binding.textInputLayoutVillage;
        textInputLayoutGroup = binding.textInputLayoutGroup;
        textInputLayoutDesignation = binding.textInputLayoutDesignation;

        // Set default values for spinners
        spinnerCounty.setPrompt("Select County");
        spinnerSubCounty.setPrompt("Select Sub-County");
    }

    /**
     * Initializes listeners for the buttons in the user interface.
     */
    private void initListeners() {
        buttonPrevious.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
    }

    /**
     * Initializes objects used in the activity, such as instances of helper classes,
     * data structures, and model objects.
     */
    private void initObjects() {
        signUpProfileActivity = new SignUpProfileActivity();
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
        user = signUpProfileActivity.user;
        subCountiesMap = new HashMap<>();
        countiesMap = new HashMap<>();
        villageMap = new HashMap<>();
        groups = new ArrayList<>();
        designation = new ArrayList<>();
        regions = new ArrayList<>();

    }

    /**
     * Handles click events for buttons in the user interface.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNext) {
            verifyFromSQLite();
            Intent intentPassword = new Intent(getApplicationContext(), SignUpPasswordActivity.class);
            startActivity(intentPassword);
        } else if (v.getId() == R.id.buttonPrevious)
            finish();
    }

    /**
     * Verifies the input data against the SQLite database to ensure that the selected designation
     * and group combination is valid and does not already exist.
     */
    private void verifyFromSQLite() {
        if (inputValidation.isSpinnerEmpty(spinnerRegion, textInputLayoutRegion, getString(R.string.select_region)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerCounty, textInputLayoutCounty, getString(R.string.select_county)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerSubCounty, textInputLayoutSubCounty, getString(R.string.select_sub_county)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerVillage, textInputLayoutVillage, getString(R.string.select_village)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerGroup, textInputLayoutGroup, getString(R.string.select_group)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerDesignation, textInputLayoutDesignation, getString(R.string.select_designation)))
            return;

        String designation = spinnerDesignation.getSelectedItem().toString();
        if (designation.equals("OVERSEER") || designation.equals("PRESIDENT") || designation.equals("DEPUTY OVERSEER") ||
                designation.equals("DEPUTY PRESIDENT") || designation.equals("SECRETARY (NATL)") || designation.equals("TREASURER (NATL)"))
            if (databaseHelper.checkDesignation(designation))
                Toast.makeText(this, getString(R.string.already_exists), Toast.LENGTH_LONG).show();

        if (designation.equals("GOVERNOR") || designation.equals("SECRETARY (DIST)") || designation.equals("TREASURER (DIST)") ||
                designation.equals("DEPUTY GOVERNOR")) {
            if (!user.getGender().equals("MALE")) {
                Toast.makeText(this, getString(R.string.designation_not_female), Toast.LENGTH_LONG).show();
                return;
            }

            if (!spinnerGroup.getSelectedItem().toString().equals("COGYOK")){
                Toast.makeText(this, getString(R.string.governor_is_cogyok), Toast.LENGTH_LONG).show();
                spinnerGroup.requestFocus();
                textInputLayoutGroup.setError(getString(R.string.governor_is_cogyok));
                return;
            }
            if (databaseHelper.checkDesignation(spinnerRegion.getSelectedItem().toString(), spinnerGroup.getSelectedItem().toString(), designation))
                Toast.makeText(this, getString(R.string.already_exists), Toast.LENGTH_LONG).show();
        }

        if (designation.equals("PASTOR") || designation.equals("ELDER")) {
            if (!user.getGender().equals("MALE")) {
                Toast.makeText(this, getString(R.string.designation_not_female), Toast.LENGTH_LONG).show();
                return;
            }

            if (!spinnerGroup.getSelectedItem().toString().equals("COGMEA")){
                Toast.makeText(this, getString(R.string.pastor_is_cogmea), Toast.LENGTH_LONG).show();
                spinnerGroup.requestFocus();
                textInputLayoutGroup.setError(getString(R.string.pastor_is_cogmea));
                return;
            }
            if (databaseHelper.checkDesignation(spinnerVillage.getSelectedItem().toString(), designation))
                Toast.makeText(this, getString(R.string.already_exists), Toast.LENGTH_LONG).show();
        }
        if (designation.equals("CHAIRPERSON") || designation.equals("SECRETARY (VILL)") || designation.equals("TREASURER (VILL"))
            if (databaseHelper.checkDesignation(spinnerRegion.getSelectedItem().toString(),spinnerVillage.getSelectedItem().toString(), spinnerGroup.getSelectedItem().toString(), designation))
                Toast.makeText(this, getString(R.string.already_exists),Toast.LENGTH_LONG).show();

        if (designation.equals("DEACON")) {
            if (!user.getGender().equals("MALE")) {
                Toast.makeText(this, getString(R.string.designation_not_female), Toast.LENGTH_LONG).show();
                return;
            }

            if (databaseHelper.checkDesignationDcn(spinnerVillage.getSelectedItem().toString(), designation))
                Toast.makeText(this, getString(R.string.already_exists), Toast.LENGTH_LONG).show();
        }

        if (designation.equals("OFFICE BEARER"))
            if (databaseHelper.checkDesignationOfficeBearer(spinnerRegion.getSelectedItem().toString(), spinnerGroup.getSelectedItem().toString(), designation))
                Toast.makeText(this, getString(R.string.already_exists),Toast.LENGTH_LONG).show();


        user.setRegion(spinnerRegion.getSelectedItem().toString());
        user.setCounty(spinnerCounty.getSelectedItem().toString());
        user.setSubCounty(spinnerSubCounty.getSelectedItem().toString());
        user.setVillage(spinnerVillage.getSelectedItem().toString());
        user.setDesignation(spinnerDesignation.getSelectedItem().toString());
        user.setUserGroup(spinnerGroup.getSelectedItem().toString());
    }

    /**
     * Replaces all items in the given list with their uppercase versions.
     *
     * @param myList The list of strings to be modified.
     */
    public void replaceAllWithUppercase(List<String> myList) {
        myList.replaceAll(String::toUpperCase);
    }

    /**
     * Replaces all keys in the given map with their uppercase versions.
     *
     * @param myMap The map of strings to be modified.
     */
    public static void replaceAllKeysWithUppercase(Map<String, List<String>> myMap) {
        // Create a new map to store the modified entries temporarily
        Map<String, List<String>> modifiedMap = new HashMap<>();

        // Create a copy of the keys to iterate over
        Set<String> keys = new HashSet<>(myMap.keySet());

        // Iterate through the keys
        for (String key : keys) {
            // Retrieve the value associated with the current key
            List<String> value = myMap.get(key);

            // Remove the entry from the original map
            myMap.remove(key);

            // Insert the entry with the uppercase key into the modified map
            modifiedMap.put(key.toUpperCase(), value);
        }

        // Insert all modified entries back into the original map
        myMap.putAll(modifiedMap);
    }
}
