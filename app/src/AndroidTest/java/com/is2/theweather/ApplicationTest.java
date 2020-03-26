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

        public static String[] randomStringFromFile() {
                try {
                        String randArray[] = new String[4];
                        //Dichiarazione array iniziale

                        String supArray[];
                        //Array di supporto per salvare i dati di randArray

                        int dim = 4;

                        int i = 0;

                        String tmp[];
                        //Array string per suddividere la stringa estratta dal file in piu' sottostringhe

                        context = getApplicationContext();
                        InputStream is = context.getResources().openRawResource(R.raw.world_cities);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                        String line;


                        int arrayDim = 0;
                        //Variabile incrementata ogni volta che viene letta una riga, al termine del while sara' la dimensione dell'array finale

                        while ((line = reader.readLine()) != null)
                                //Lettura da file e verifica sia diverso da null
                        {
                                arrayDim++;
                                tmp = line.split(",");
                                //Splitto la stringa letta da file in sottostringhe in base al carattere virgola ","

                                if (i < dim)
                                {
                                        randArray[i] = tmp[0];
                                        //il dato che ci interessa del file in ingresso e' nella posizione 0 di tmp
                                        i = i + 1;
                                } else
                                        //Se l'array e' saturato creo un array di dimensione dim*2, alloco sempre il doppio cosi da risparmiare nelle allocazioni totali,
                                        // ad esempio se N fossero il numero di stringhe avremmo N allocazioni mentre con questo modo avremmo log(2,N) allocazioni
                                {
                                        dim = dim * 2;
                                        supArray = new String[dim];
                                        for (int j = 0; j < dim / 2; j++)
                                                //Salvo il contenuto di randArray in supArray (nelle stesse posizioni)
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
                        for (i = 0; i < arrayDim; i++) {
                                supArray[i] = randArray[i];
                        }
                        return supArray;
                } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                }
        }


        public static String[] randomizeArray(String Values[])
        //Creo array randomico dato un array in ingresso
        {
                String randArray[] = new String[Values.length];
                int min = 0;
                int max = Values.length - 1;
                int i = 0;
                int random_int;
                for (i = 0; i < Values.length - 1; i++) {
                        random_int = (int)(Math.random() * ((max - 1) - min + 1) + min);
                        //Ogni volta che estraggo un elemento, lo vado ad estrarre generando la posizione dell'array compresa tra min e max

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
        public void testRand() {
                String[] Str = randomStringFromFile();

                String[] randStr = randomizeArray(Str);

                for (int i = 0; i < randStr.length - 1; i++) {

                        System.out.println(randStr[i]);
                }

                for (int i = 0; i < randStr.length - 1; i++) {

                        //TODO
                        //INSERT ALL CITIES AND TEST THE OUTPUTS
                }
        }

}