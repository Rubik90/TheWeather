package com.is2.theweather;

import android.app.Application;
import android.content.Context;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;


@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

        private static Context context;
        private EditText city_name2;

        public String[] randomStringFromFile()
        {
                try
                {
                        String[] randArray = new String[4];
                        String[] supArray;
                        int dim = 4;
                        int i = 0;


                        String[] tmp;
                        context = getApplicationContext();
                        InputStream is = context.getResources().openRawResource(R.raw.world_cities);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                        String line;

                        int arrayDim = 0;
                        while((line=reader.readLine())!=null)
                        {
                                arrayDim++;
                                tmp = line.split(",");
                                if(i<dim)
                                {
                                        randArray[i] = tmp[0];
                                        i = i + 1;
                                }
                                else
                                {
                                        //Creo un array piu' grande
                                        dim = dim * 2;
                                        supArray = new String[dim];
                                        for(int j=0;j<dim/2;j++)
                                        {
                                                supArray[j] = randArray[j];
                                        }
                                        randArray = supArray;
                                        randArray[i] = tmp[0];
                                        i = i + 1;
                                }
                        }
                        supArray = new String[arrayDim];
                        is.close();
                        for(i=0;i<arrayDim;i++)
                        {
                                supArray[i] = randArray [i];
                        }
                        return supArray;
                }
                catch(IOException e)
                {
                        e.printStackTrace();
                        return null;
                }
        }

        public String[] randomizeArray(String[] Values)
        {
                String[] randArray = new String[Values.length];
                int min = 0;
                int max = Values.length - 1;
                int i;
                int random_int;
                for(i=0;i<Values.length-1;i++)
                {
                        random_int = (int)(Math.random() * ((Values.length-1) - min + 1) + min);
                        randArray[i] = Values[random_int];
                        Values[random_int] = Values[max];
                        max = max - 1;
                }
                return randArray;

        }

        private Application app;

        @Before
        public void setUp() throws Exception {

        }

        @After
        public void tearDown() throws Exception {

        }

        @Test
        public void testRand()
        {
                String[] Str = randomStringFromFile();

                String[] randStr = randomizeArray(Str);

                for (int i=0;i<randStr.length-1;i++){

                        System.out.println(randStr[i]);
                }

                for (int i=0;i<randStr.length-1;i++){

                        //TODO
                        //INSERT ALL CITIES AND TEST THE OUTPUTS
                }
        }

}


