/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.examples.tweets.GetRetweets;

/**
 *
 * @author hans
 */
public class BlackboardManager {

    // Token: 1531890990-w1hDA3K7IHeluVkt25LLmhxurJJswM8xkzQpO4b
    // Secret: h9GhUQNsLvKJmFPrV1bOLygRXkXkyLONgXI6g
    // Singleton
    
    
    
    
    private BlackboardManager() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("CLdqTkThxhPABMOvOZ64w")
                .setOAuthConsumerSecret("SYr84dM9KTVHz0lRB9UQM0SxyJuFoBJLZ50doJGYZSs")
                .setOAuthAccessToken("1531890990-w1hDA3K7IHeluVkt25LLmhxurJJswM8xkzQpO4b")
                .setOAuthAccessTokenSecret("h9GhUQNsLvKJmFPrV1bOLygRXkXkyLONgXI6g");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        getBlackboardTweets();
        //initTwitter();
    }

    public static BlackboardManager getInstance() {
        return BlackboardManagerHolder.INSTANCE;
    }

    private static class BlackboardManagerHolder {

        private static final BlackboardManager INSTANCE = new BlackboardManager();
    }
    // Class
    Twitter twitter;
    static ArrayList<BlackboardTweet> blackboardTweets = new ArrayList<BlackboardTweet>();
    static HashMap correlation = new HashMap();
    
    public static void addUser(int ID) {
        try {
            ArrayList<BlackboardTweet> tweets = blackboardTweets;
            correlation.put(ID, tweets);
        } catch (Exception e) {
            System.out.println("BlckboardManager - addUser: " + e);
        }
    }

    public static ArrayList<BlackboardTweet> getUserTweets(int ID) {
        ArrayList<BlackboardTweet> result = null;
        if (correlation.containsKey(ID)) {
            result = (ArrayList<BlackboardTweet>) correlation.get(ID);
        }
        return result;
    }
    
    public class BlackboardTweet implements Comparable<BlackboardTweet> {
        public String username;
        public String tweet;
        public Date time;

        @Override
        public int compareTo(BlackboardTweet t) {
            return this.time.compareTo(t.time);
        }

        public BlackboardTweet(String username, String tweet, Date time) {
            this.username = username;
            this.tweet = tweet;
            this.time = time;
        }
    }
    
    public ArrayList<BlackboardTweet> getTweets() {
        return new ArrayList<BlackboardTweet>(blackboardTweets);
    }
    
    private void getBlackboardTweets() {
        try {
            Query query = new Query("HSHLBlackboard #hshlblackboard");
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                blackboardTweets.add(new BlackboardTweet(status.getUser().getScreenName(), status.getText(), status.getCreatedAt()));
                System.out.println(status.getCreatedAt() + "/@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(BlackboardManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void test() {
//        try {
//            Query query = new Query("HSHLBlackboard");
//            QueryResult result = twitter.search(query);
//            for (Status status : result.getTweets()) {
//                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
//            }
//        } catch (TwitterException ex) {
//            Logger.getLogger(BlackboardManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void initTwitter() {
        try {
            // The factory instance is re-useable and thread safe.
            twitter = TwitterFactory.getSingleton();
            twitter.setOAuthConsumer("CLdqTkThxhPABMOvOZ64w", "SYr84dM9KTVHz0lRB9UQM0SxyJuFoBJLZ50doJGYZSs");
            RequestToken requestToken = twitter.getOAuthRequestToken();
            AccessToken accessToken = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (null == accessToken) {
                System.out.println("Open the following URL and grant access to your account:");
                System.out.println(requestToken.getAuthorizationURL());
                System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
                String pin = br.readLine();
                try {
                    if (pin.length() > 0) {
                        accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                    } else {
                        accessToken = twitter.getOAuthAccessToken();
                    }
                } catch (TwitterException te) {
                    if (401 == te.getStatusCode()) {
                        System.out.println("Unable to get the access token.");
                    } else {
                        te.printStackTrace();
                    }
                }
            }
            //persist to the accessToken for future reference.
            storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
            //Status status = twitter.updateStatus(args[0]);
            //System.out.println("Successfully updated the status to [" + status.getText() + "].");
            //System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(BlackboardManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TwitterException ex) {
            Logger.getLogger(BlackboardManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void storeAccessToken(long id, AccessToken accessToken) {
        System.out.println("ID" + id + "/Token: " + accessToken.getToken() + " Secret: " + accessToken.getTokenSecret());

    }
}
