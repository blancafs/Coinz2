package com.example.blanca.coinz2;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.common.base.Joiner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class MySharedPreferences {
    private static final String tag = "MySharedPreference";
    private static final String CURR_USER_NAME = "username";
    private static final String CURR_MODE = "current_mode";
    private static final String CURR_CHALLENGE = "current_challenge";
    private static final String BANKED_COINS = "banked_coins";
    private static final String WALLET_COINS = "wallet_coins";
    private static final String TOTAL_COINS = "total_coins";
    private static final String SENT_TOTAL = "sent_total";
    private static final String SENT_COINS = "sent_coins";
    private static final String SPECIAL_COINS = "special_coins";
    private static final String GOLD = "gold";
    private static final String SAVED_IMAGES = "saved_images";
    private static final String TOTAL_DISTANCE = "total_distance";
    private static final String MEMBERS = "members";

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /////////////
    // Setters //
    /////////////

    // Sets current username ========================= //
    static void setUserName(Context context, String email) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CURR_USER_NAME, email);
        editor.apply();
        Log.d(tag, "[setUserName]: current username " + email + " saved in MyPreferences.");
    }

    static void resetUserName(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CURR_USER_NAME, "");
        editor.apply();
        Log.d(tag, "[resetUserName]: current username reset in MyPreferences.");
    }

    // Sets current 'mode' - whether hard (True) or not (False) ====== //
    static void setCurrMode(Context context, Boolean mode) {
        String email = getUserName(context);
        String playermodes = getSharedPreferences(context).getString(CURR_MODE, "0");
        // Formatting how the string will be saved in preferences //
        String result = email + ":" + String.valueOf(mode); // format email:level, email:level ...
        String answer = "";

        // if not empty, simply add to existing string, unless username already there with a value in which case we replace the value and the substring
        if (!(playermodes.length()<2)) {
            String[] levels = playermodes.split(",");
            String[] finals = levels.clone();
            for (int i=0; i<levels.length; i++) {
                String s = levels[i];
                // if already a string with that username, replace with string with new level
                if (s.contains(email)) {
                    Log.d(tag, "[setCurrMode] string s in playermodes has occurence of username ! ===================");
                    finals[i] = result;
                    Log.d(tag, "[setCurrMode] string res " + result + " replaced old string===================");
                } else {
                    finals[i] = s;
                }
            }
            answer = Joiner.on(",").join(finals);
            Log.d(tag, "[setCurrMode]: current playermodes not empty, " + answer + " added to saved string.");
        } else {
            answer = result;
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CURR_MODE, answer);
        editor.apply();
        Log.d(tag, "[setCurrMode]: current playermodes " + answer + " saved in preferences=============");
    }

    static void setCurrChallenge(Context context, String challenge){
        String email = getUserName(context);

        // Challenge contains
        String currChallenge = getSharedPreferences(context).getString(CURR_CHALLENGE, "");
        String result = email + ":" + challenge; // format email:lat/long/time
        String answer;

        // if not empty, simply add to existing string, unless username already there with a value in which case we replace the value and the substring
        if (!(currChallenge.length()<2)) {
            String[] challenges = currChallenge.split(",");
            String[] finals = challenges.clone();
            for (int i=0; i<challenges.length; i++) {
                String s = challenges[i];
                // if already a string with that username, replace with string with new level
                if (s.contains(email)) {
                    Log.d(tag, "[setCurrChallenge] string s in challenges has occurence of username ! ===================");
                    finals[i] = result;
                    Log.d(tag, "[setCurrChallenge] string res " + result + " replaced old string===================");
                } else {
                    finals[i] = s;
                }
            }
            answer = Joiner.on(",").join(finals);
            Log.d(tag, "[setCurrChallenge]: current challenges not empty, " + answer + " added to saved string.");
        } else {
            answer = result;
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CURR_CHALLENGE, answer);
        editor.apply();
        Log.d(tag, "[setCurrChallenge]: current challenges " + answer + " saved in preferences=============");

    }

    static void setGoldDirectly(Context context, double gold) {
        String email = getUserName(context);
        String golds = getSharedPreferences(context).getString(GOLD, "0");
        String answer = "";
        // if string not empty
        if (!(golds.length() < 2)) {
            String[] coins = golds.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding level for that player
                String s = coins[i];
                if (s.contains(email)) {
                    String ans = email+ ":" + String.valueOf(gold);
                    coins[i] = ans;
                    Log.d(tag, "[addToGold] ans " + ans + " replaced old string s in gold " + s);
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else { // if it is empty
            answer = email + ":" + String.valueOf(gold);
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(GOLD, answer);
        editor.apply();
        Log.d(tag, "[addToGold]: final coin string " + answer + " saved in preferences");
    }

    static void setTotalDistance(Context context, double distance) {
        String email = getUserName(context);
        String distances = getSharedPreferences(context).getString(TOTAL_DISTANCE, "0");
        String answer = "";
        // if string not empty
        if (!(distances.length() < 2)) {
            String[] coins = distances.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding distance travelled for that player
                String s = coins[i];
                if (s.contains(email)) {
                    String ans = email+ ":" + String.valueOf(distance);
                    coins[i] = ans;
                    Log.d(tag, "[setTotalDistance] ans " + ans + " replaced old string s in distance " + s);
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else { // if it is empty
            answer = email + ":" + String.valueOf(distance);
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(TOTAL_DISTANCE, answer);
        editor.apply();
        Log.d(tag, "[setTotalDistance]: final coin string " + answer + " saved in preferences");
    }

    static void setPlayerImage(Context context, String filepath) {
        String email = getUserName(context);
        String imgs = getSharedPreferences(context).getString(SAVED_IMAGES, "0");
        String answer;
        // if string not empty
        if (!(imgs.length() < 2)) {
            String[] coins = imgs.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding level for that player
                String s = coins[i];
                if (s.contains(email)) {
                    String ans = email + ":" + filepath;
                    coins[i] = ans;
                    Log.d(tag, "[setPlayerImage] ans " + ans + " replaced old string s in saved images" + s);
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else { // if it is empty
            answer = email + ":" + filepath;
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SAVED_IMAGES, answer);
        editor.apply();
        Log.d(tag, "[setPlayerImage]: final images string " + answer + " saved in preferences");
    }

    static void addMember(Context context, String email) {
        String members = getSharedPreferences(context).getString(MEMBERS, "0");

        if (!(members.contains(email))) {
            members += ","+email;
        } else {
            members = email;
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(MEMBERS, members);
        editor.apply();
        Log.d(tag, "[addMember]: coin string " + members + " saved in preferences");

    }

    /////////////
    // Adders ///
    /////////////
    static void sendCoin(Context context, Coin coin, String email) {
        String totalcoins = getSharedPreferences(context).getString(GOLD, "0");
        String answer = "";
        // if string not empty
        if (!(totalcoins.length() < 2)) {
            String[] coins = totalcoins.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding intended player to send to
                String s = coins[i];
                if (s.contains(email)) {
                    Log.d(tag, "[sendCoin] string s in members has occurence of username ! ===================");
                    String ans = email;
                    double level = Integer.parseInt(s.split(":")[1]);
                    // add the gold value of coin sent to their gold total
                    level = level + coin.getGold();
                    Log.d(tag, "[sendCoin] final gold value is " + level + " ============");
                    ans = ans + ":" + level;
                    coins[i] = ans;
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else { // if it is empty
            answer = email + ":" + String.valueOf(1);
        }
        addToSentCoins(context, coin);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(GOLD, answer);
        editor.apply();
        Log.d(tag, "[sendCoin]: final coin string " + answer + " saved in gold preferences");
    }

    static void addSpecialCoin(Context context, Coin coin) {
        String email = getUserName(context);
        String specialcoins = getSharedPreferences(context).getString(SPECIAL_COINS, "0");
        String result = email + ":" + coin.stringify(); // format of coin stringify id + "-" + value + "-" + currency;
        int counter = 0;

        if (!(specialcoins.length()<2)) {
            Log.d(tag, "[addSpecialCoin] specialcoins in preferences not empty! ===================");
            // as not empty, we split the preference string
            String[] coins = specialcoins.split(","); // so now strings are email:coin string
            for (String s:coins) {
                if (s.contains(coin.getId()) && s.contains(email)) {
                    counter++;
                    // if coin already in saved string, counter will not be 0
                }
            }
            if (counter==0) {
                // if not already in saved preferences for that user, add formatted string to preferences string
                result = specialcoins + "," + result;
            } else {
                // if that coin is already saved for that user, keep string as is
                result = specialcoins;
                Log.d(tag, "[addSpecialCoin] coin was already in saved special coins string in preferences =========");
            }
        }
        removeWalletCoin(context,coin);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SPECIAL_COINS, result);
        editor.apply();
        Log.d(tag, "[addSpecialCoin]: coin string " + result + " saved in preferences");
    }

    static void add2CoinTotal(Context context) {
        String email = getUserName(context);
        String totalcoins = getSharedPreferences(context).getString(TOTAL_COINS, "0");
        String answer = "";
        // if string not empty
        if (!(totalcoins.length() < 2)) {
            String[] coins = totalcoins.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding level for that player
                String s = coins[i];
                if (s.contains(email)) {
                    Log.d(tag, "[add2CoinTotal] string s in totalcoins has occurence of username ! ===================");
                    String ans = email;
                    Integer level = Integer.parseInt(s.split(":")[1]);
                    level += 1;
                    ans = ans + ":" + level;
                    coins[i] = ans;
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else { // if it is empty
            answer = email + ":" + String.valueOf(1);
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(TOTAL_COINS, answer);
        editor.apply();
        Log.d(tag, "[add2CoinTotal]: final coin string " + answer + " saved in preferences");
    }

    static void add2SentTotal(Context context, Coin coin, int where) { // where is 1 if wallet, 0 if special coins
        String email = getUserName(context);
        String sentcoins = getSharedPreferences(context).getString(SENT_TOTAL, "0");
        String answer = "";
        // if string not empty
        if (!(sentcoins.length() < 2)) {
            String[] coins = sentcoins.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding level for that player
                String s = coins[i];
                if (s.contains(email)) {
                    Log.d(tag, "[add2SentTotal] string s in sentcoins has occurence of username ! ===================");
                    String ans = email;
                    Integer level = Integer.parseInt(s.split(":")[1]);
                    level += 1;
                    ans = ans + ":" + level;
                    coins[i] = ans;
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else { // if it is empty
            answer = email + ":" + String.valueOf(1);
        }
        if (where==1) {
            removeWalletCoin(context,coin);
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SENT_TOTAL, answer);
        editor.apply();
        Log.d(tag, "[add2SentTotal]: final coin string " + answer + " saved in preferences");
    }

    static void addToSentCoins(Context context, Coin coin) {
        String email = getUserName(context);
        String specialcoins = getSharedPreferences(context).getString(SENT_COINS, "0");
        String result = email + ":" + coin.stringify(); // format of coin stringify id + "-" + value + "-" + currency;
        int counter = 0;

        if (!(specialcoins.length()<2)) {
            Log.d(tag, "[addToSentCoins] sent coins in preferences not empty! ===================");
            // as not empty, we split the preference string
            String[] coins = specialcoins.split(","); // so now strings are email:coin string
            for (String s:coins) {
                if (s.contains(coin.getId()) && s.contains(email)) {
                    counter++;
                    // if coin already in saved string, counter will not be 0
                }
            }
            if (counter==0) {
                // if not already in saved preferences for that user, add formatted string to preferences string
                result = specialcoins + "," + result;
            } else {
                // if that coin is already saved for that user, keep string as is
                result = specialcoins;
                Log.d(tag, "[addToSentCoins] coin was already in saved sent coins string in preferences =========");
            }
        }
        removeWalletCoin(context,coin);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SENT_COINS, result);
        editor.apply();
        Log.d(tag, "[addToSentCoins]: coin string " + result + " saved in preferences");
    }

    static void addBankCoin(Context context, Coin coin, int where) {
        String email = getUserName(context);
        String bankedcoins = getSharedPreferences(context).getString(BANKED_COINS, "0");
        String result = email + ":" + coin.stringify(); // format of coin stringify id + "-" + value + "-" + currency;
        Integer counter = 0;

        // checking if saved preferences string is empty
        if (!(bankedcoins.length()<2)) {
            // if not empty, split preference string
            String[] coins = bankedcoins.split(",");
            for (String s:coins) {
                if (s.contains(coin.getId()) && s.contains(email)) {
                    counter++;
                    // if coin already in saved string, counter will not be 0
                }
            }
            if (counter==0) {
                // if not already in saved preferences for that user, add formatted string to preferences string
                result = bankedcoins + "," + result;
            } else {
                // if that coin is already saved for that user, keep string as is
                result = bankedcoins;
                Log.d(tag, "[addBankCoin] coin was already in saved banked coins string in preferences =========");
            }
        } // if empty

        if (where==0) {
            removeWalletCoin(context, coin);
        } else if(where==2) {
            removeSpecialCoin(context, coin);
        }

        // if added to banked coins, added to gold
        addToGold(context, coin);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(BANKED_COINS, result);
        editor.apply();
        Log.d(tag, "[addBankCoin]: coin string " + result + " saved in preferences");
    }

    // adds string email:coin-details, email:coin-details, for each coin in wallet
    static void addWalletCoin(Context context, Coin coin) {
        String email = getUserName(context);
        String walletcoins = getSharedPreferences(context).getString(WALLET_COINS, "0");
        String result = email + ":" + coin.stringify(); // format of coin stringify id + "-" + value + "-" + currency;
        int counter = 0;

        if (!(walletcoins.length()<2)) {
            // as not empty, we split the preference string
            String[] coins = walletcoins.split(",");
            for (String s:coins) {
                if (s.contains(coin.getId()) && s.contains(email)) {
                    counter++;
                    // if coin already in saved string, counter will not be 0
                }
            }
            if (counter==0) {
                // if not already in saved preferences for that user, add formatted string to preferences string
                result = walletcoins + "," + result;
            } else {
                // if that coin is already saved for that user, keep string as is
                result = walletcoins;
                Log.d(tag, "[addWalletCoin] coin was already in saved wallet coins string in preferences =========");
            }
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        add2CoinTotal(context);
        editor.putString(WALLET_COINS, result);
        editor.apply();
        Log.d(tag, "[addWalletCoin]: coin string " + result + " saved in preferences");
    }

    static void addToGold(Context context, Coin coin) {
        String email = getUserName(context);
        String golds = getSharedPreferences(context).getString(GOLD, "0");
        double value = coin.getGold();
        String answer = "";
        // if string not empty
        if (!(golds.length() < 2)) {
            String[] coins = golds.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding level for that player
                String s = coins[i];
                if (s.contains(email)) {
                    String ans = email;
                    double gold = Double.parseDouble(s.split(":")[1]);
                    gold += value;
                    ans = ans + ":" + gold;
                    coins[i] = ans;
                    Log.d(tag, "[addToGold] ans " + ans + " replaced old string s in gold " + s);
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else { // if it is empty
            answer = email + ":" + String.valueOf(value);
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(GOLD, answer);
        editor.apply();
        Log.d(tag, "[addToGold]: final coin string " + answer + " saved in preferences");
    }

    //////////////
    // Removers //
    //////////////

    static void removeWalletCoin(Context context, Coin coin) {
        String email = getUserName(context);
        String walletCoins = getSharedPreferences(context).getString(WALLET_COINS, "0");
        String answer = "";
        // if string not empty
        if (!(walletCoins.length() < 2)) {
            String[] coins = walletCoins.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding level for that player
                String s = coins[i];
                if (s.contains(email) && s.contains(coin.getId())) {
                    coins[i] = "";
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else {
            // if empty
            Log.d(tag, "[removeWalletCoin] walletcoinz was empty! nothing done ==============");
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(WALLET_COINS, answer);
        editor.apply();
        Log.d(tag, "[removeWalletCoin]: final coin string " + answer + " saved in preferences");
    }

    static void removeSpecialCoin(Context context, Coin coin) {
        String email = getUserName(context);
        String speccoins = getSharedPreferences(context).getString(SPECIAL_COINS, "0");
        String answer = "";
        // if string not empty
        if (!(speccoins.length() < 2)) {
            String[] coins = speccoins.split(",");
            for (Integer i = 0; i < coins.length; i++) {
                // when we get to string holding level for that player
                String s = coins[i];
                if (s.contains(email) && s.contains(coin.getId())) {
                    coins[i] = "";
                } else {
                    // otherwise leave string the same
                    coins[i] = s;
                }
            }
            answer = Joiner.on(",").join(coins);
        } else {
            // if empty
            Log.d(tag, "[removeSpecialCoin] speccoins was empty! nothing done ==============");
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SPECIAL_COINS, answer);
        editor.apply();
        Log.d(tag, "[removeSpecialCoin]: final coin string " + answer + " saved in preferences");
    }

    /////////////
    // Getters //
    /////////////

    // Get email of current user =====================//
    static String getUserName(Context context) {
        String ans = getSharedPreferences(context).getString(CURR_USER_NAME, "");
        if (ans.equals("")) {
            return null;
        }
        return ans;
    }

    // Get total coins for player ====================//
    static int getTotalCoins(Context context, String email) {
        String totals = getSharedPreferences(context).getString(TOTAL_COINS, "0");
        if (totals.length()<2) {
            return 0;
        }
        // have to check for the name and get the value with it
        String[] total = totals.split(","); // as we have formatted the string as "email:mode", "email:mode" ...
        for (String s:total) {
            if (s.contains(email)) {
                Integer actotal = Integer.parseInt(s.split(":")[1]);
                return actotal;
            }
        } // if no string matches name, return 0
        return 0;
    }

    // Get current mode ==============================// where false=standard mode and true=hard mode
    static Boolean getCurrentMode(Context context, String email) {
        String modes = getSharedPreferences(context).getString(CURR_MODE, "0");
        if (modes.length()<2) {
            Log.d(tag, "[getCurrentMode]: returning false");
            return false;
        }
        String[] mode = modes.split(","); // as we have formatted the string as "email:mode", "email:mode" ...
        for (String s:mode) {
            if (s.contains(email)) {
                Boolean acMode = Boolean.parseBoolean(s.split(":")[1]);
                Log.d(tag, "[getCurrentMode]: returning "+ String.valueOf(acMode));
                return acMode;
            }
        }
        Log.d(tag, "[getCurrentMode]: returning false");
        return false;
    }

    static String getPlayerImage(Context context, String email) {
        String images = getSharedPreferences(context).getString(SAVED_IMAGES, "");
        String pimg = "";

        if (images.length()<2) {
            return "";
        }
        String[] imgs = images.split(",");
        for (String a:imgs) {
            if (a.contains(email)) {
                pimg = a.split(":")[1];
            }
        }
        return pimg;
    }

    static String getCurrChallenge(Context context, String email){
        // Challenge contains
        String currChallenge = getSharedPreferences(context).getString(CURR_CHALLENGE, "");
        if(currChallenge.length()<2){
            return "";
        }
        String challenge = "";
        String[] challenges = currChallenge.split(",");
        for(String entry : challenges){
            if(entry.contains(email)){
                if(!(currChallenge.split(":").length < 2)){
                    challenge = currChallenge.split(":")[1];
                }
            }
        }

        // Get the second part and return it
        Log.d(tag, "[getCurrChallenge]: returns: "+ challenge);
        return challenge;
    }

    static double getGoldTotal(Context context, String email) {
        String golds = getSharedPreferences(context).getString(GOLD, "0");
        if (golds.length()< 2) {
            return 0;
        }
        String[] gold = golds.split(",");
        for (String s:gold) {
            if (s.contains(email)) {
                double actgold = Double.parseDouble(s.split(":")[1]);
                return actgold;
            }
        }
        return 0;
    }

    static double getTotalDistance(Context context, String email) {
        String distances = getSharedPreferences(context).getString(TOTAL_DISTANCE, "0");
        if (distances.length()< 2) {
            return 0;
        }
        String[] dist = distances.split(",");
        for (String s:dist) {
            if (s.contains(email)) {
                double actdist = Double.parseDouble(s.split(":")[1]);
                return actdist;
            }
        }
        return 0;
    }

    // Get wallet coins ==============================//
    static ArrayList<Coin> getWalletCoins(Context context, String email) {
        String coins = getSharedPreferences(context).getString(WALLET_COINS, "0");
        ArrayList<Coin> empty = new ArrayList<Coin>();
        if (coins.length()<2) {
            Log.d(tag, "[getWalletCoins] no coins in wallet coins string in preferences.");
            return empty; // if nothing in string
        }
        // make string array with all contents of walletcoins
        String[] walletcoins = coins.split(",");
        ArrayList<Coin> finalcoins = new ArrayList<>();

        // to ensure we only downloads today's
        Date date = new Date();
        SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");
        String today = dformat.format(date);

        for (String s:walletcoins) {
            // check for given email, if present keep string and get coin, then add it to arraylist
            if (s.contains(email) && s.contains(today)) {
                String coinstring = s.split(":")[1];
                String[] features = coinstring.split("/"); // coin string format email:id-value-currency
                Coin walcoin = new Coin(features[0],features[1], features[2]); // Coin(String id, String value, String currency)
                walcoin.setDate(features[3]); // features 3 == date the coin was picked up
                Log.d(tag, "[getWalletCoins] stringify of walcoin gives " + walcoin.stringify() + "==========================");
                finalcoins.add(walcoin);
                Log.d(tag, "[getWalletCoins] coin retrieved from wallet memory, added to walletcoins arraylist.");
            }
        }
        return finalcoins;
    }

    static ArrayList<Coin> getBankedCoins(Context context, String email) {
        String coins = getSharedPreferences(context).getString(BANKED_COINS, "0");
        ArrayList<Coin> empty = new ArrayList<Coin>();
        if (coins.length()<2) {
            Log.d(tag, "[getBankedCoins] no coins in banked coins string in preferences.");
            return empty;
        }
        // make string array with all contents of walletcoins
        String[] bankedcoins = coins.split(",");
        ArrayList<Coin> finalcoins = new ArrayList<Coin>();

        // to ensure we only downloads today's
        Date date = new Date();
        SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");
        String today = dformat.format(date);

        for (String s:bankedcoins) {
            // check for given email, if present keep string and get coin, then add it to arraylist
            if (s.contains(email) && s.contains(today)) {
                String coinstring = s.split(":")[1];
                String[] features = coinstring.split("/"); // coin string format email:id-value-currency
                Coin walcoin = new Coin(features[0], features[1], features[2]); // Coin(String id, String value, String currency)
                walcoin.setDate(features[3]);
                Log.d(tag, "[getBankedCoins] walcoin created, walcoin stringify " + walcoin.stringify() + " =========================");
                finalcoins.add(walcoin);
                Log.d(tag, "[getBankedCoins] coin " + features[0] + "retrieved from wallet memory, added to bankedcoins arraylist.");
            }
        }
        return finalcoins;
    }

    static ArrayList<Coin> getStoredSpecials(Context context, String email) {
        String coins = getSharedPreferences(context).getString(SPECIAL_COINS, "0");
        ArrayList<Coin> empty = new ArrayList<Coin>();
        if (coins.length()<2) {
            Log.d(tag, "[getStoredSpecials] no coins in special coins string in preferences.");
            return empty;
        }
        // make string array with all contents of walletcoins
        String[] specialcoins = coins.split(",");
        ArrayList<Coin> finalcoins = new ArrayList<Coin>();

        // to ensure we only downloads today's
        Date date = new Date();
        SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");
        String today = dformat.format(date);

        for (String s:specialcoins) {
            // check for given email, if present keep string and get coin, then add it to arraylist
            if (s.contains(email) && s.contains(today)) {
                String coinstring = s.split(":")[1];
                String[] features = coinstring.split("/");
                // coin string format email:id-value-currency
                Coin walcoin = new Coin(features[0], features[1], features[2]); // Coin(String id, String value, String currency)
                Log.d(tag, "[getStoredSpecials] walcoin created, walcoin stringify " + walcoin.stringify() + " =========================");
                walcoin.setDate(features[3]);
                finalcoins.add(walcoin);
                Log.d(tag, "[getStoredSpecials] coin " + features[0] + "retrieved from special coin memory, added to special coins arraylist.");
            }
        }
        return finalcoins;
    }

    static ArrayList<Coin> getSentCoins(Context context, String email) {
        String coins = getSharedPreferences(context).getString(SENT_COINS, "0");
        ArrayList<Coin> empty = new ArrayList<Coin>();
        if (coins.length()<2) {
            Log.d(tag, "[getStoredSpecials] no coins in sent coins string in preferences.");
            return empty;
        }
        // make string array with all contents of walletcoins
        String[] specialcoins = coins.split(",");
        ArrayList<Coin> finalcoins = new ArrayList<Coin>();

        // to ensure we only downloads today's
        Date date = new Date();
        SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");
        String today = dformat.format(date);

        for (String s:specialcoins) {
            // check for given email, if present keep string and get coin, then add it to arraylist
            if (s.contains(email) && s.contains(today)) {
                String coinstring = s.split(":")[1];
                String[] features = coinstring.split("/");
                // coin string format email:id-value-currency
                Coin walcoin = new Coin(features[0], features[1], features[2]); // Coin(String id, String value, String currency)
                Log.d(tag, "[getSentCoins] walcoin created, walcoin stringify " + walcoin.stringify() + " =========================");
                walcoin.setDate(features[3]);
                finalcoins.add(walcoin);
                Log.d(tag, "[getSentCoins] coin " + features[0] + "retrieved from sent coin memory, added to sent coins arraylist.");
            }
        }
        return finalcoins;
    }

    static int getTotalSentCoins(Context context, String email) {
        String level = getSharedPreferences(context).getString(SENT_TOTAL, "0");
        if (level.length()< 2) {
            return 0;
        }
        String[] levels = level.split(",");
        for (String s:levels) {
            if (s.contains(email)) {
                int actlevel = Integer.parseInt(s.split(":")[1]);
                return actlevel;
            }
        }
        return 0;
    }

    static ArrayList<String> getFriends(Context context, String email) {
        String members = getSharedPreferences(context).getString(MEMBERS, "0");
        ArrayList<String> empty = new ArrayList<>();

        if (members.length()<2) {
            Log.d(tag, "[getFriends] no people in members string in preferences.");
            empty.add("");
            return empty;
        }
        // make string array with all contents of members
        String[] mems = members.split(",");

        for (String s:mems) {
            // check for given email, if present keep string and get string, then add it to arraylist
            if (!(s.contains(email))) {
                empty.add(s);
                Log.d(tag, "[getFriends] found " + s + " in members string");
            }
        }
        return empty;
    }

   
}
