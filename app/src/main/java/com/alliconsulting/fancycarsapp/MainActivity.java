package com.alliconsulting.fancycarsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alliconsulting.fancycarsapp.model.CarDetails;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<CarDetails> mCarDetails = new ArrayList<>();

    RecyclerView recyclerView = null;
    RecyclerViewAdapter adapter = null;
    LinearLayoutManager layoutManager = null;

    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previousTotal = 0;

    private final int gulp_size = 20;
    private ArrayList<String> carImages = null;
    private int viewThreshold = 10;

    private Faker carDetailsFaker = null;

    private void stubCarImages(){
        carImages = new ArrayList<>();

        carImages.add("https://cdn.wallpapersafari.com/20/51/vKI5cz.jpg");
        carImages.add("https://cdn.wallpapersafari.com/53/46/e2jyms.jpg");
        carImages.add("https://images.wallpaperscraft.com/image/icona_vulcano_2013_exotic_car_102281_1024x768.jpg");
        carImages.add("http://eskipaper.com/images/exotic-cars-40.jpg");
        carImages.add("https://cdn.wallpapersafari.com/33/58/bd9Q2G.jpg");
        carImages.add("https://wallpaperaccess.com/full/148063.jpg");
        carImages.add("http://eskipaper.com/images/exotic-cars-22.jpg");
        carImages.add("https://cdn.wallpapersafari.com/81/92/RMp2ej.jpg");
        carImages.add("http://bighpartybuses.com/images/1.png");
        carImages.add("https://wallpaperaccess.com/full/13714.jpg");

        carImages.add("http://1.bp.blogspot.com/-qZX1rBg4vfA/UtcKZS3mBKI/AAAAAAAANEE/11fjNqF5118/s1600/Exotic+Car+Pictures+Wallpaper2.jpg");
        carImages.add("https://i.pinimg.com/originals/56/e4/c1/56e4c1240921fe64dc2c3d847224a470.jpg");
        carImages.add("https://wallpapercave.com/wp/bHk2l8n.jpg");
        carImages.add("https://images.wallpaperscraft.com/image/lamborghini_murcielago_lp670_4_sv_front_jackdarton_glare_night_lights_95095_300x255.jpg");
        carImages.add("https://wallpaperbro.com/img/341673.jpg");
        carImages.add("http://www.seriouswheels.com/pics-2009/def/2009-Daytona-Coupe-Le-Mans-Edition-by-Exotic-Auto-Restoration-Rear-And-Side-1-1024x768.jpg");
        carImages.add("https://www.extrememobilesounds.com/wp-content/uploads/2017/08/Photo-Nov-10-3-09-58-PM-1024x768.jpg");
        carImages.add("https://cdn.wallpapersafari.com/79/88/R704PX.jpg");
        carImages.add("https://wallpaperaccess.com/full/386231.jpg");
        carImages.add("https://cdn.allwallpaper.in/wallpapers/1024x768/9992/gt-gt-r-r34-blue-paint-exotic-car-1024x768-wallpaper.jpg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carDetailsFaker = new Faker();
        stubCarImages();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);

        Log.d(TAG,"onCreate: started.");

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if(dy>0){
                    if(isLoading){
                        if(totalItemCount>previousTotal){
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }

                    if(!isLoading && (totalItemCount-visibleItemCount)<=(pastVisibleItems+viewThreshold)){
                        performPagination();
                        isLoading=true;
                    }
                }
            }
        });

        initImageBitMaps();
    }

    private ArrayList<String> randomizeImageURLs(){
        ArrayList<String> newRandomizedURLs = new ArrayList<>();

        Random rand = new Random(System.currentTimeMillis());
        int randInt = 0;
        for(int i = 0; i< gulp_size; i++) {
            randInt = rand.nextInt(gulp_size);
            newRandomizedURLs.add(carImages.get(randInt));
        }
        return newRandomizedURLs;
    }

    private ArrayList<String> randomizeImageNames(){
        ArrayList<String> newRandomizedNames = new ArrayList<>();

        for(int i = 0; i< gulp_size; i++) {
            newRandomizedNames.add(carDetailsFaker.name().fullName());
        }
        return newRandomizedNames;
    }

    private ArrayList<CarDetails> randomizeCarDetails(){
        ArrayList<CarDetails> newRandomizedCarDetails = new ArrayList<>();
        for(int i = 0; i< gulp_size; i++) {
            newRandomizedCarDetails.add(new CarDetails(carDetailsFaker));
        }
        return newRandomizedCarDetails;
    }

    private void initImageBitMaps(){
        Log.d(TAG,"initImageBitMaps: preparing bitmaps.");

        mImageUrls.addAll(randomizeImageURLs());
        mCarDetails.addAll(randomizeCarDetails());
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init");

        adapter = new RecyclerViewAdapter(this,mImageUrls,mCarDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
    }

    private void performPagination(){
        Toast.makeText(MainActivity.this, "Getting more cars...", Toast.LENGTH_SHORT).show();
        adapter.updateListWithImages(randomizeImageURLs(),randomizeCarDetails());
    }
}
