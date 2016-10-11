package com.example.cristianbaita.MusicMap.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristianbaita.MusicMap.R;
import com.example.cristianbaita.MusicMap.animators.Animators;
import com.example.cristianbaita.MusicMap.helper.ImageLoader;
import com.example.cristianbaita.MusicMap.instagram.InstagramApp;

import java.util.HashMap;

import com.example.cristianbaita.MusicMap.pojo.GoogleLoginManager;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.facebook.FacebookException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class LoginActivity extends AppCompatActivity {


    //LOginIN
    private ImageView facebookLogInImage, googleLogInImage;
    private Button logInButton;

    private boolean facebookTurn;

    private Animators animators;

    //GOOGLE SIGN IN
    private GoogleApiClient mGoogleApiClient;
    private SignInButton googleButton;
    private static int RC_SIGN_IN = 9001;
    private GoogleLoginManager loginGoogle;
    private GoogleSignInAccount account2;
    private Context loginContext = this;

    //INSTAGRAM SIGN IN
    private InstagramApp mApp;
    public Button btnConnect;
    private String username;
    private HashMap<String, String> userInfoHashmap = new HashMap<String, String>();
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == InstagramApp.WHAT_FINALIZE) {
                userInfoHashmap = mApp.getUserInfo();
                //System.out.println(userInfoHashmap.toString());
            } else if (msg.what == InstagramApp.WHAT_FINALIZE) {
                Toast.makeText(LoginActivity.this, "Check your network.",
                        Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });

    //FACEBOOK SIGN IN
//    private CallbackManager facebookCallbackManager;
//    public LoginButton facebookbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FACEBOOK STUFF
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        facebookCallbackManager = CallbackManager.Factory.create();
        // END FACEBOOK STUFF

        animators = new Animators();
        setContentView(R.layout.login_layout);

        facebookLogInImage = (ImageView) findViewById(R.id.facebookLogInImageView);
        googleLogInImage = (ImageView) findViewById(R.id.googleLogInImageView);
        logInButton = (Button) findViewById(R.id.loginButton);


        animators.fadeInScaleInAnimation(facebookLogInImage, loginContext);
        animators.fadeOutScaleOutAnimation(googleLogInImage, loginContext);

        facebookTurn = true;


        facebookLogInImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!facebookTurn) {
                    animators.fadeInScaleInAnimation(facebookLogInImage, loginContext);
                    animators.fadeOutScaleOutAnimation(googleLogInImage, loginContext);
                    facebookTurn = !facebookTurn;
                }

            }
        });

        googleLogInImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (facebookTurn) {
                    animators.fadeInScaleInAnimation(googleLogInImage, loginContext);
                    animators.fadeOutScaleOutAnimation(facebookLogInImage, loginContext);
                    facebookTurn = !facebookTurn;
                }
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginContext, HomepageActivity.class);
                startActivity(intent);
            }
        });
    }






        //GOOGLE STUFF

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//
//        googleButton = (SignInButton) findViewById(R.id.googlebutton);
//        googleButton.setVisibility(View.GONE);
//        googleButton.setOnClickListener(this);

        //END GOOGLE STUFF


            //FACEBOOK

//        facebookbutton = (LoginButton) findViewById(R.id.facebookbutton);
//        facebookbutton.setVisibility(View.GONE);
//
//        facebookbutton.registerCallback(facebookCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
//
//                startActivity(intent);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException e) {
//
//            }
//        });
//
//        //FACEBOOK
//    }


//    @Override
//    public void onClick(View v) {
//
//        if (v == btnConnect) {
//            connectOrDisconnectUser();
//        }
//        else {
//
//            switch (v.getId()) {
//                case R.id.googlebutton: {
//                    System.out.println("E ok");
//                    signIn();
//                    //loginGoogle = new GoogleLoginManager();
//                    //loginGoogle.signIn(mGoogleApiClient);
//                    //account2 = loginGoogle.getAccount();
//                    //System.out.println(account2.getDisplayName());
//                    break;
//                }
//                // ...
//            }
//        }
//    }

            public void signIn() {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//
//
//        }
//    }

            private void handleSignInResult(GoogleSignInResult result) {
                //Log.d(TAG, "handleSignInResult:" + result.isSuccess());
                if (result.isSuccess()) {
                    // Signed in successfully, show authenticated UI.
                    GoogleSignInAccount acct = result.getSignInAccount();
                    System.out.println(acct.getDisplayName());
                }
            }

            private void saveUser() {

                SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                SharedPreferences.Editor editor = settings.edit();

                //new ImageLoader(LoginActivity.this).DisplayImage(userInfoHashmap.get(InstagramApp.TAG_PROFILE_PICTURE), ivProfile);
                //tvName.setText(userInfoHashmap.get(InstagramApp.TAG_USERNAME));

                //tvNoOfFollowing.setText(userInfoHashmap.get(InstagramApp.TAG_FOLLOWS));
                //tvNoOfFollwers.setText(userInfoHashmap.get(InstagramApp.TAG_FOLLOWED_BY));

                editor.putString("Username", userInfoHashmap.get(InstagramApp.TAG_USERNAME));
                System.out.println(userInfoHashmap.get(InstagramApp.TAG_USERNAME));
                editor.putString("Followers", userInfoHashmap.get(InstagramApp.TAG_FOLLOWED_BY));
                System.out.println(userInfoHashmap.get(InstagramApp.TAG_USERNAME));

                editor.putString("Following", userInfoHashmap.get(InstagramApp.TAG_FOLLOWS));

                editor.commit();
            }

            private void connectOrDisconnectUser() {
                if (mApp.hasAccessToken()) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(
                            LoginActivity.this);
                    builder.setMessage("Disconnect from Instagram?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            mApp.resetAccessToken();
                                            //llAfterLoginView.setVisibility(View.GONE);
                                            btnConnect.setText("Connect");
                                        }
                                    })
                            .setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            dialog.cancel();
                                        }
                                    });
                    final AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    mApp.authorize();
                }
            }

            public void displayInfoDialogView() {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                alertDialog.setTitle("Profile Info");

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.profile_view, null);
                alertDialog.setView(view);
                ImageView ivProfile = (ImageView) view.findViewById(R.id.ivProfileImage);
                TextView tvName = (TextView) view.findViewById(R.id.tvUserName);
                username = tvName.getText().toString();

                TextView tvNoOfFollwers = (TextView) view.findViewById(R.id.tvNoOfFollowers);
                TextView tvNoOfFollowing = (TextView) view.findViewById(R.id.tvNoOfFollowing);

                new ImageLoader(LoginActivity.this).DisplayImage(userInfoHashmap.get(InstagramApp.TAG_PROFILE_PICTURE), ivProfile);
                tvName.setText(userInfoHashmap.get(InstagramApp.TAG_USERNAME));

                tvNoOfFollowing.setText(userInfoHashmap.get(InstagramApp.TAG_FOLLOWS));
                tvNoOfFollwers.setText(userInfoHashmap.get(InstagramApp.TAG_FOLLOWED_BY));

                alertDialog.create().show();
            }

            public String getUsername() {
                return username;
            }
        }



