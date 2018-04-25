package br.com.senaijandira.atividadebitcoin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView txt_bitcoin, txt_litecoin, txt_btc_cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_bitcoin = (TextView) findViewById(R.id.txt_bitcoin);
        txt_litecoin = (TextView) findViewById(R.id.txt_litecoin);
        txt_btc_cash = (TextView) findViewById(R.id.txt_btc_cash);

        consultarPrecos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        consultarPrecos();
        return super.onOptionsItemSelected(item);
    }

    public void consultarPrecos(){
        final String url_btc = "https://www.mercadobitcoin.net/api/btc/ticker/";
        final String url_ltc = "https://www.mercadobitcoin.net/api/ltc/ticker/";
        final String url_bch = "https://www.mercadobitcoin.net/api/bch/ticker/";

        new AsyncTask<Void, Void, Void>(){
            String retorno_btc;
            String retorno_ltc;
            String retorno_bch;
            @Override
            protected Void doInBackground(Void... voids) {
                retorno_btc = HttpConnection.get(url_btc);
                retorno_ltc = HttpConnection.get(url_ltc);
                retorno_bch = HttpConnection.get(url_bch);
                Log.d("retorno", retorno_btc);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), retorno_btc, Toast.LENGTH_LONG).show();
                try{
                    JSONObject retJasonBtc = new JSONObject(retorno_btc);
                    JSONObject tickerBtc = retJasonBtc.getJSONObject("ticker");
                    JSONObject retJasonLtc = new JSONObject(retorno_ltc);
                    JSONObject tickerLtc = retJasonLtc.getJSONObject("ticker");
                    JSONObject retJasonBch = new JSONObject(retorno_bch);
                    JSONObject tickerBch = retJasonBch.getJSONObject("ticker");

                    txt_bitcoin.setText(tickerBtc.get("last").toString());
                    txt_litecoin.setText(tickerLtc.get("last").toString());
                    txt_btc_cash.setText(tickerBch.get("last").toString());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }.execute();
    }

    public void consultarPreco(View v){
        final String url_btc = "https://www.mercadobitcoin.net/api/btc/ticker/";
        final String url_ltc = "https://www.mercadobitcoin.net/api/ltc/ticker/";
        final String url_bch = "https://www.mercadobitcoin.net/api/bch/ticker/";

        new AsyncTask<Void, Void, Void>(){
            String retorno_btc;
            String retorno_ltc;
            String retorno_bch;
            @Override
            protected Void doInBackground(Void... voids) {
                retorno_btc = HttpConnection.get(url_btc);
                retorno_ltc = HttpConnection.get(url_ltc);
                retorno_bch = HttpConnection.get(url_bch);
                Log.d("retorno", retorno_btc);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), retorno_btc, Toast.LENGTH_LONG).show();
                try{
                    JSONObject retJasonBtc = new JSONObject(retorno_btc);
                    JSONObject tickerBtc = retJasonBtc.getJSONObject("ticker");
                    JSONObject retJasonLtc = new JSONObject(retorno_ltc);
                    JSONObject tickerLtc = retJasonLtc.getJSONObject("ticker");
                    JSONObject retJasonBch = new JSONObject(retorno_bch);
                    JSONObject tickerBch = retJasonBch.getJSONObject("ticker");

                    txt_bitcoin.setText(tickerBtc.get("last").toString());
                    txt_litecoin.setText(tickerLtc.get("last").toString());
                    txt_btc_cash.setText(tickerBch.get("last").toString());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }.execute();
    }
}
