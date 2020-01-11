package org.stormroboticsnj.stormuserradar2020;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import org.stormroboticsnj.stormuserradar2020.dao.StormDao;
import org.stormroboticsnj.stormuserradar2020.models.Whoosh;
import org.stormroboticsnj.stormuserradar2020.ui.main.SectionsPagerAdapter;

/**
 * This Activity is the main data collection activity. It has a tabbed layout; each tab corresponds
 * to a different fragment, as defined in SectionsPagerAdapter. It contains private variables for
 * each field of data collected and increment/decrement or setter as well as getter methods for
 * each.
 */
public class MainActivity extends AppCompatActivity implements Scoring.OnFragmentInteractionListener, Endgame.OnFragmentInteractionListener{
    /* brought from StartActivity */
    private int team;
    private int match;
    private boolean alliance; //true = red

    /* recorded in this activity */

    private int scoreLVL1 = 0;
    private int scoreLVL2 = 0;
    private int scoreLVL3 = 0;

    public void incScoreLVL1 () {
        scoreLVL1++;

    private int score = 0;
    private int scoreTwo = 0;
    private int ePowerCell1 = 0;
    private int ePowerCell2 = 0;
    private int ePowerCell3 = 0;
    private int aPowerCell1 = 0;
    private int aPowerCell2;
    private int aPowerCell3;
    private int aPowerCellPickup;

    public void incaPowerCell1 () {
        aPowerCell1++;
    }
    public void decaPowerCell1 () {
        aPowerCell1--;
    }
    public int getaPowerCell1 () {
        return aPowerCell1;
    }
    public void incaPowerCell2 () {
        aPowerCell2++;
    }
    public void decaPowerCell2 () {
        aPowerCell2--;
    }
    public int getaPowerCell2 () {
        return aPowerCell2;
    }
    public void incaPowerCell3 () {
        aPowerCell3++;
    }
    public void decaPowerCell3 () {
        aPowerCell3--;
    }
    public int getaPowerCell3 () {
        return aPowerCell3;
    }
    public void incaPowerCellPickup () {
        aPowerCellPickup++;
    }
    public void decaPowerCellPickup () {
        aPowerCellPickup--;
    }
    public int getaPowerCellPickup () {
        return aPowerCellPickup;
    }

    public void incePowerCell1(){
        ePowerCell1++;

    }

    public void decePowerCell1()  {
        ePowerCell1--;
    }
    public void incePowerCell2() {
        ePowerCell2++;
    }
    public void decePowerCell2() {
        ePowerCell2--;
    }
    public void incePowerCell3() {
        ePowerCell3++;
    }
    public void decePowerCell3() {
        ePowerCell3--;
    }
    public int getePowerCell1() {return ePowerCell1;}
    public int getePowerCell2() {return ePowerCell2;}
    public int getePowerCell3() {return ePowerCell3;}
    public void incScore () {
        score++;

    }
    public void decScoreLVL1 () {scoreLVL1--; }
    public int getScoreLVL1 () {
        return scoreLVL1;
    }

    public void incScoreLVL2 () {
        scoreLVL2++;
    }
    public void decScoreLVL2 () {
        scoreLVL2--;
    }
    public int getScoreLVL2 () {
        return scoreLVL2;
    }

    public void incScoreLVL3 () {
        scoreLVL3++;
    }
    public void decScoreLVL3 () {
        scoreLVL3--;
    }
    public int getScoreLVL3 () {
        return scoreLVL3;
    }

    private AppDatabase db; //built on creation of Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout is activity_main

        /* this handles the switching between fragments. see ui.main.SectionsPagerAdapter */
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(this, getSupportFragmentManager()); //\\

        ViewPager viewPager = findViewById(R.id.view_pager); //this is the area that changes to each fragment
        viewPager.setAdapter(sectionsPagerAdapter); //tell it to be controlled by the instance
        TabLayout tabs = findViewById(R.id.tabs); //this is the physical tabs
        tabs.setupWithViewPager(viewPager); //sync the two together


        /* get data from Intent (passed from StartActivity) */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            team = extras.getInt("team");
            match = extras.getInt("match");
            alliance = extras.getBoolean("alliance");
        }

        /* get database, or build if it doesn't exist. This exact line must be included in the onCreate
        method of every Activity that uses the database. db can be a class-wide variable or local
        within onCreate. */
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "stormdb").allowMainThreadQueries().build(); //build database
    }

    /* submit button pressed */
    public void submit() {

        StormDao stormDao = db.stormDao(); //get interface object

        /* create Whoosh */
        Whoosh whoosh = new Whoosh(team, match);

        whoosh.setAlliance(alliance);


        whoosh.setScore(scoreLVL1);
        whoosh.setScoreTwo(scoreLVL2);

        //whoosh.setScore(score);
        //whoosh.setScoreTwo(scoreTwo);

        stormDao.insertWhooshes(whoosh);

        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}