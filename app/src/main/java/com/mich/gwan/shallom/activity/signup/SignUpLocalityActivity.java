/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 23
 */

package com.mich.gwan.shallom.activity.signup;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.LoginActivity;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivitySignupLocalityBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

public class SignUpLocalityActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerCounty;
    private Spinner spinnerSubCounty;
    private Spinner spinnerVillage;
    private Spinner spinnerGroup;
    private Spinner spinnerDesignation;

    private TextInputLayout textInputLayoutImage;
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

        // Ad items to Region list
        regions.add("Select Region");
        regions.add("NAIROBI REGION");
        regions.add("COAST REGION");
        regions.add("MURANG'A REGION");
        regions.add("ELDORET REGION");
        regions.add("NAKURU REGION");
        regions.add("EMBU REGION");
        regions.add("KIRINYAGA REGION");
        regions.add("MERU REGION");
        regions.add("NAROK REGION");
        regions.add("KISUMU REGION");
        regions.add("TURKANA REGION");
        regions.add("NORTH EASTERN REGION");
        regions.add("LAMU REGION");
        regions.add("LAIKIPIA REGION");

        List<String> nairobiCounties = new ArrayList<>();
        nairobiCounties.add("022 Kiambu");
        nairobiCounties.add("047 Nairobi City");

        List<String> coastCounties = new ArrayList<>();
        coastCounties.add("001 Mombasa");
        coastCounties.add("002 Kwale");
        coastCounties.add("003 Kilifi");
        coastCounties.add("004 Tana River");
        coastCounties.add("005 Lamu");
        coastCounties.add("006 Taita Taveta");

        List<String> northEasternCounties = new ArrayList<>();
        northEasternCounties.add("007 Garissa");
        northEasternCounties.add("008 Wajir");
        northEasternCounties.add("009 Mandera");
        northEasternCounties.add("010 Marsabit");

        counties = new ArrayList<String>();
        counties.add("Select County");

        counties.add("011 Isiolo");
        counties.add("012 Meru");
        counties.add("013 Tharaka-Nithi");
        counties.add("014 Embu");
        counties.add("015 Kitui");
        counties.add("016 Machakos");
        counties.add("017 Makueni");
        counties.add("018 Nyandarua");
        counties.add("019 Nyeri");
        counties.add("020 Kirinyaga");
        counties.add("021 Murang'a");
        counties.add("");
        counties.add("023 Turkana");
        counties.add("024 West Pokot");
        counties.add("025 Samburu");
        counties.add("026 Trans Nzoia");
        counties.add("027 Uasin Gishu");
        counties.add("028 Elgeyo Marakwet");
        counties.add("029 Nandi");
        counties.add("030 Baringo");
        counties.add("031 Laikipia");
        counties.add("032 Nakuru");
        counties.add("033 Narok");
        counties.add("034 Kajiado");
        counties.add("035 Kericho");
        counties.add("036 Bomet");
        counties.add("037 Kakamega");
        counties.add("038 Vihiga");
        counties.add("039 Bungoma");
        counties.add("040 Busia");
        counties.add("041 Siaya");
        counties.add("042 Kisumu");
        counties.add("043 Homa Bay");
        counties.add("044 Migori");
        counties.add("045 Kisii");
        counties.add("046 Nyamira");
        counties.add("");

        List<String> nairobiSubCounties = new ArrayList<>();
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
        mombasaSubCounties.add("Changamwe");
        mombasaSubCounties.add("Jomvu");
        mombasaSubCounties.add("Kisauni");
        mombasaSubCounties.add("Likoni");
        mombasaSubCounties.add("Mvita");
        mombasaSubCounties.add("Nyali");


        List<String> kwaleSubCounties = new ArrayList<>();
        kwaleSubCounties.add("Kinango");
        kwaleSubCounties.add("Lunga Lunga");
        kwaleSubCounties.add("Msambweni");
        kwaleSubCounties.add("Matuga");

        List<String> kilifiSubCounties = new ArrayList<>();
        kilifiSubCounties.add("Ganze");
        kilifiSubCounties.add("Kaloleni");
        kilifiSubCounties.add("Kilifi North");
        kilifiSubCounties.add("Kilifi South");
        kilifiSubCounties.add("Magarini");
        kilifiSubCounties.add("Malindi");
        kilifiSubCounties.add("Rabai");

        List<String> tanaRiverSubCounties = new ArrayList<>();
        tanaRiverSubCounties.add("Bura");
        tanaRiverSubCounties.add("Galole");
        tanaRiverSubCounties.add("Garsen");

        List<String> lamuSubCounties = new ArrayList<>();
        lamuSubCounties.add("Lamu East");
        lamuSubCounties.add("Lamu West");

        List<String> taitaTavetaSubCounties = new ArrayList<>();
        taitaTavetaSubCounties.add("Mwatate");
        taitaTavetaSubCounties.add("Taveta");
        taitaTavetaSubCounties.add("Voi");
        taitaTavetaSubCounties.add("Wundanyi");

        List<String> garisaSubCounties = new ArrayList<>();
        garisaSubCounties.add("Daadab");
        garisaSubCounties.add("Fafi");
        garisaSubCounties.add("Garissa Township");
        garisaSubCounties.add("Hulugho");
        garisaSubCounties.add("Ijara");
        garisaSubCounties.add("Lagdera");
        garisaSubCounties.add("Balambala");

        List<String> wajirSubCounties = new ArrayList<>();
        wajirSubCounties.add("Eldas");
        wajirSubCounties.add("Tarbaj");
        wajirSubCounties.add("Wajir East");
        wajirSubCounties.add("Wajir North");
        wajirSubCounties.add("Wajir South");
        wajirSubCounties.add("Wajir West");

        List<String> manderaSubCounties = new ArrayList<>();
        manderaSubCounties.add("Banissa");
        manderaSubCounties.add("Lafey");
        manderaSubCounties.add("Mandera East");
        manderaSubCounties.add("Mandera North");
        manderaSubCounties.add("Mandera South");
        manderaSubCounties.add("Mandera West");

        List<String> marsabitSubCounties = new ArrayList<>();
        marsabitSubCounties.add("Laisamis");
        marsabitSubCounties.add("Moyale");
        marsabitSubCounties.add("North Hor");
        marsabitSubCounties.add("Saku");

        List<String> isioloSubCounties = new ArrayList<>();
        isioloSubCounties.add("Isiolo");
        isioloSubCounties.add("Merti");
        isioloSubCounties.add("Garbatulla");

        List<String> meruSubCounties = new ArrayList<>();
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
        tharakaNithiSubCounties.add("Tharaka North");
        tharakaNithiSubCounties.add("Tharaka South");
        tharakaNithiSubCounties.add("Chuka");
        tharakaNithiSubCounties.add("Igambango’mbe");
        tharakaNithiSubCounties.add("Maara");
        tharakaNithiSubCounties.add("Chiakariga and Muthambi");

        List<String> embuSubCounties = new ArrayList<>();
        embuSubCounties.add("Manyatta");
        embuSubCounties.add("Mbeere North");
        embuSubCounties.add("Mbeere South");
        embuSubCounties.add("Runyenjes");

        List<String> kituiSubCounties = new ArrayList<>();
        kituiSubCounties.add("Kitui West");
        kituiSubCounties.add("Kitui Central");
        kituiSubCounties.add("Kitui Rural");
        kituiSubCounties.add("Kitui South");
        kituiSubCounties.add("Kitui East");
        kituiSubCounties.add("Mwingi North");
        kituiSubCounties.add("Mwingi West");
        kituiSubCounties.add("Mwingi Central");

        List<String> machakosSubCounties = new ArrayList<>();
        machakosSubCounties.add("Kathiani");
        machakosSubCounties.add("Machakos Town");
        machakosSubCounties.add("Masinga");
        machakosSubCounties.add("Matungulu");
        machakosSubCounties.add("Mavoko");
        machakosSubCounties.add("Mwala");
        machakosSubCounties.add("Yatta");

        List<String> makueniSubCounties = new ArrayList<>();
        makueniSubCounties.add("Kaiti");
        makueniSubCounties.add("Kibwezi West");
        makueniSubCounties.add("Kibwezi East");
        makueniSubCounties.add("Kilome");
        makueniSubCounties.add("Makueni");
        makueniSubCounties.add("Mbooni");

        List<String> nyadaruaSubCounties = new ArrayList<>();
        nyadaruaSubCounties.add("Kinangop");
        nyadaruaSubCounties.add("Kipipiri");
        nyadaruaSubCounties.add("Ndaragwa");
        nyadaruaSubCounties.add("Ol-Kalou");
        nyadaruaSubCounties.add("Ol Joro Orok");

        List<String> nyeriSubCounties = new ArrayList<>();
        nyeriSubCounties.add("Kieni East");
        nyeriSubCounties.add("Kieni West");
        nyeriSubCounties.add("Mathira East");
        nyeriSubCounties.add("Mathira West");
        nyeriSubCounties.add("Mukurweini");
        nyeriSubCounties.add("Nyeri Town");
        nyeriSubCounties.add("Othaya");
        nyeriSubCounties.add("Tetu");

        List<String> kirinyagaSubCounties = new ArrayList<>();
        kirinyagaSubCounties.add("Kirinyaga Central");
        kirinyagaSubCounties.add("Kirinyaga East");
        kirinyagaSubCounties.add("Kirinyaga West");
        kirinyagaSubCounties.add("Mwea East");
        kirinyagaSubCounties.add("Mwea West");

        List<String> murangaSubCounties = new ArrayList<>();
        murangaSubCounties.add("Gatanga");
        murangaSubCounties.add("Kahuro");
        murangaSubCounties.add("Kandara");
        murangaSubCounties.add("Kangema");
        murangaSubCounties.add("Kigumo");
        murangaSubCounties.add("Kiharu");
        murangaSubCounties.add("Mathioya");
        murangaSubCounties.add("Murang’a South");

        List<String> kiambuSubCounties = new ArrayList<>();
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
        turkanaSubCounties.add("Loima");
        turkanaSubCounties.add("Turkana Central");
        turkanaSubCounties.add("Turkana East");
        turkanaSubCounties.add("Turkana North");
        turkanaSubCounties.add("Turkana South");

        List<String> westPokotSubCounties = new ArrayList<>();
        westPokotSubCounties.add("Central Pokot");
        westPokotSubCounties.add("North Pokot");
        westPokotSubCounties.add("Pokot South");
        westPokotSubCounties.add("West Pokot");

        List<String> samburuSubCounties = new ArrayList<>();
        samburuSubCounties.add("Samburu East");
        samburuSubCounties.add("Samburu North");
        samburuSubCounties.add("Samburu West");

        List<String> transNzoiaSubCounties = new ArrayList<>();
        transNzoiaSubCounties.add("Cherangany");
        transNzoiaSubCounties.add("Endebess");
        transNzoiaSubCounties.add("Kiminini");
        transNzoiaSubCounties.add("Kwanza");
        transNzoiaSubCounties.add("Saboti");

        List<String> uasinGishuSubCounties = new ArrayList<>();
        uasinGishuSubCounties.add("Ainabkoi");
        uasinGishuSubCounties.add("Kapseret");
        uasinGishuSubCounties.add("Kesses");
        uasinGishuSubCounties.add("Moiben");
        uasinGishuSubCounties.add("Soy");
        uasinGishuSubCounties.add("Turbo");

        List<String> elgeyoMarakwetSubCounties = new ArrayList<>();
        elgeyoMarakwetSubCounties.add("Keiyo North");
        elgeyoMarakwetSubCounties.add("Keiyo South");
        elgeyoMarakwetSubCounties.add("Marakwet East");
        elgeyoMarakwetSubCounties.add("Marakwet West");

        List<String> nandiSubCounties = new ArrayList<>();
        nandiSubCounties.add("Aldai");
        nandiSubCounties.add("Chesumei");
        nandiSubCounties.add("Emgwen");
        nandiSubCounties.add("Mosop");
        nandiSubCounties.add("Nandi Hills");
        nandiSubCounties.add("Tindiret");

        List<String> baringoSubCounties = new ArrayList<>();
        baringoSubCounties.add("Baringo Central");
        baringoSubCounties.add("Baringo North");
        baringoSubCounties.add("Baringo South");
        baringoSubCounties.add("Eldama Ravine");
        baringoSubCounties.add("Mogotio");
        baringoSubCounties.add("Tiaty");

        List<String> laikipiaSubCounties = new ArrayList<>();
        laikipiaSubCounties.add("Laikipia Central");
        laikipiaSubCounties.add("Laikipia East");
        laikipiaSubCounties.add("Laikipia North");
        laikipiaSubCounties.add("Laikipia West");
        laikipiaSubCounties.add("Nyahururu");

        List<String> nakuruSubCounties = new ArrayList<>();
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
        narokSubCounties.add("Narok East");
        narokSubCounties.add("Narok North");
        narokSubCounties.add("Narok South");
        narokSubCounties.add("Narok West");
        narokSubCounties.add("Transmara East");
        narokSubCounties.add("Transmara West");

        List<String> kajiadoSubCounties = new ArrayList<>();
        kajiadoSubCounties.add("sinya");
        kajiadoSubCounties.add("ajiado Central");
        kajiadoSubCounties.add("ajiado North");
        kajiadoSubCounties.add("oitokitok");
        kajiadoSubCounties.add("ashuuru");

        List<String> kerichoSubCounties = new ArrayList<>();
        kerichoSubCounties.add("Ainamoi");
        kerichoSubCounties.add("Belgut");
        kerichoSubCounties.add("Bureti");
        kerichoSubCounties.add("Kipkelion East");
        kerichoSubCounties.add("Kipkelion West");
        kerichoSubCounties.add("Soin/Sigowet");

        List<String> bometSubCounties = new ArrayList<>();
        bometSubCounties.add("Bomet Central");
        bometSubCounties.add("Bomet East");
        bometSubCounties.add("Chepalungu");
        bometSubCounties.add("Konoin");
        bometSubCounties.add("Sotik");

        List<String> kakamegaSubCounties = new ArrayList<>();
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
        vihigaSubCounties.add("Emuhaya");
        vihigaSubCounties.add("Hamisi");
        vihigaSubCounties.add("Luanda");
        vihigaSubCounties.add("Sabatia");
        vihigaSubCounties.add("Vihiga");

        List<String> bungomaSubCounties = new ArrayList<>();
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
        busiaSubCounties.add("Budalangi");
        busiaSubCounties.add("Butula");
        busiaSubCounties.add("Funyula");
        busiaSubCounties.add("Nambele");
        busiaSubCounties.add("Teso North");
        busiaSubCounties.add("Teso South");

        List<String> siayaSubCounties = new ArrayList<>();
        siayaSubCounties.add("Alego Usonga");
        siayaSubCounties.add("Bondo");
        siayaSubCounties.add("Gem");
        siayaSubCounties.add("Rarieda");
        siayaSubCounties.add("Ugenya");
        siayaSubCounties.add("Unguja");

        List<String> kisumuSubCounties = new ArrayList<>();
        kisumuSubCounties.add("Kisumu Central");
        kisumuSubCounties.add("Kisumu East");
        kisumuSubCounties.add("Kisumu West");
        kisumuSubCounties.add("Muhoroni");
        kisumuSubCounties.add("Nyakach");
        kisumuSubCounties.add("Nyando");
        kisumuSubCounties.add("Seme");

        List<String> homaBaySubCounties = new ArrayList<>();
        homaBaySubCounties.add("Homabay Town");
        homaBaySubCounties.add("Kabondo");
        homaBaySubCounties.add("Karachwonyo");
        homaBaySubCounties.add("Kasipul");
        homaBaySubCounties.add("Mbita");
        homaBaySubCounties.add("Ndhiwa");
        homaBaySubCounties.add("Rangwe");
        homaBaySubCounties.add("Suba");

        List<String> migoriSubCounties = new ArrayList<>();
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
        nyamiraSubCounties.add("Borabu");
        nyamiraSubCounties.add("Manga");
        nyamiraSubCounties.add("Masaba North");
        nyamiraSubCounties.add("Nyamira North");
        nyamiraSubCounties.add("Nyamira South");

        /**
         * Villages arraylists
         */

        List<String> nyaliVillages = new ArrayList<>();
        nyaliVillages.add("KISIMANI");

        List<String> jomvuVillages = new ArrayList<>();
        jomvuVillages.add("CHANGAMWE");

        List<String> msambeniVillages = new ArrayList<>();
        msambeniVillages.add("SHIMBA HILLS");
        msambeniVillages.add("UKUNDA");

        List<String> kilifiSouthVillages = new ArrayList<>();
        kilifiSouthVillages.add("KILIFI");
        kilifiSouthVillages.add("MSABAHA");
        kilifiSouthVillages.add("GEDE");
        kilifiSouthVillages.add("DIDA");

        List<String> ganzeVillages = new ArrayList<>();
        ganzeVillages.add("SHARIANI");

        List<String> kaloleniVillages = new ArrayList<>();
        kaloleniVillages.add("MKAPUNI");

        List<String> roysambuVillages = new ArrayList<>();
        roysambuVillages.add("MARURUI");

        List<String> kamukunjiVillages = new ArrayList<>();
        kamukunjiVillages.add("EASTLEIGH - HQ");

        List<String> embakasiNorthVillages = new ArrayList<>();
        embakasiNorthVillages.add("RUAI");

        List<String> kabeteVillages = new ArrayList<>();
        kabeteVillages.add("GUDOK");

        List<String> jujaVillages = new ArrayList<>();
        jujaVillages.add("JUJA");

        List<String> kiambuVillages = new ArrayList<>();
        kiambuVillages.add("KIAMBU");

        List<String> ruiruVillages = new ArrayList<>();
        ruiruVillages.add("RUIRU - SATELITE");

        List<String> limuruVillages = new ArrayList<>();
        limuruVillages.add("LIMURU - TIEKUNU");

        List<String> thikaTownVillages = new ArrayList<>();
        thikaTownVillages.add("THIKA - KIGANJO");


        // Convert all to uppercase
        replaceAllWithUppercase(counties);
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

        // put key (COUNTY) and value (SUB-COUNTY-LIST) to the subCountyMap
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

        // Put key (SUB-COUNTY) and value (VILLAGE-LIST) to villageMap
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

        // Set up ArrayAdapter for counties
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, counties);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCounty.setAdapter(dataAdapter);

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

        // Add items to Group list
        groups.add("Select Group");
        groups.add("COGYOK");
        groups.add("MEWAK");
        groups.add("COGMEA");
        groups.add("MEYO");
        groups.add("LEWA");

        // Add values to Designation list
        designation.add("Select Designation");
        designation.add("CHAIRPERSON");
        designation.add("OFFICE BEARER");
        designation.add("GOVERNOR");
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

    private void initViews() {
        spinnerCounty = binding.spinnerCounty;
        spinnerSubCounty = binding.spinnerSubCounty;
        spinnerVillage = binding.spinnerVillage;
        spinnerGroup = binding.spinnerGroup;
        spinnerDesignation = binding.spinnerDesignation;
        buttonPrevious = binding.buttonPrevious;
        buttonNext = binding.buttonNext;
        textInputLayoutImage = binding.textInputLayoutImage;
        textInputLayoutCounty = binding.textInputLayoutCounty;
        textInputLayoutSubCounty = binding.textInputLayoutSubCounty;
        textInputLayoutVillage = binding.textInputLayoutVillage;
        textInputLayoutGroup = binding.textInputLayoutGroup;
        textInputLayoutDesignation = binding.textInputLayoutDesignation;

        // Set default values for spinners
        spinnerCounty.setPrompt("Select County");
        spinnerSubCounty.setPrompt("Select Sub-County");
    }

    private void initListeners() {
        buttonPrevious.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
    }

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNext)
            verifyFromSQLite();
        else if (v.getId() == R.id.buttonPrevious)
            finish();
    }

    private void verifyFromSQLite() {
        if (inputValidation.isSpinnerEmpty(spinnerCounty, textInputLayoutCounty, getString(R.string.select_a_value)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerSubCounty, textInputLayoutSubCounty, getString(R.string.select_a_value)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerVillage, textInputLayoutVillage, getString(R.string.select_a_value)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerGroup, textInputLayoutGroup, getString(R.string.select_a_value)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerDesignation, textInputLayoutDesignation, getString(R.string.select_a_value)))
            return;

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

        // Iterate through the original map
        for (Map.Entry<String, List<String>> entry : myMap.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();

            // Remove the entry from the original map
            myMap.remove(key);

            // Insert the entry with the uppercase key into the modified map
            modifiedMap.put(key.toUpperCase(), value);
        }

        // Insert all modified entries back into the original map
        myMap.putAll(modifiedMap);
    }

}
