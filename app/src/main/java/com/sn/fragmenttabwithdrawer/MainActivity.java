package com.sn.fragmenttabwithdrawer;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.app.Activity;

import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.sn.fragmenttabwithdrawer.TabA.AppTabAFirstFragment;
import com.sn.fragmenttabwithdrawer.TabB.AppTabBFirstFragment;
import com.sn.fragmenttabwithdrawer.TabC.AppTabCFirstFragment;
import com.sn.fragmenttabwithdrawer.TabD.AppTabDFirstFragment;
import com.sn.fragmenttabwithdrawer.TabE.AppTabEFirstFragment;
import com.sn.fragmenttabwithdrawer.constant.AppConstants;
import com.sn.fragmenttabwithdrawer.constant.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private CharSequence drawerTitle;
    private CharSequence title;

    String[] andoridVeriosnArray;

    /* Your Tab host */
    private TabHost mTabHost;

    /* A HashMap of stacks, where we use tab identifier as keys..*/
    private HashMap<String, Stack<Fragment>> mStacks;

    /*Save current tabs identifier in this..*/
    private String mCurrentTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
        }

        title = drawerTitle = getTitle();
        andoridVeriosnArray = new String[] { "Tab A", "Tab B", "Tab C","Tab D", "Tab E"};

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerList = (ListView) findViewById(R.id.drawerList);

        // Set shadow to drawer
      /*  drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
*/
        // Set adapter to drawer
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, andoridVeriosnArray));

        // set up click listener on drawer
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        // set app icon to behave as action to toggle navigation drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //for Set Title
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        // ActionBarDrawerToggle ties together the the interactions the sliding
        // drawer and the app icon
    /*    drawerToggle = new ActionBarDrawerToggle(this, // Host Activity
                drawerLayout, // layout container for navigation drawer
                 R.string.app_name, // Open Drawer Description
                R.string.app_name) // Close Drawer Description
        {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };*/
        drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /* Called when drawer is closed */
            public void onDrawerClosed(View view) {
                //Put your code here
            }

            /* Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                //Put your code here
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);



        mStacks   =   new HashMap<String, Stack<Fragment>>();
        mStacks.put(AppConstants.TAB_A, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_B, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_C, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_D, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_E, new Stack<Fragment>());


        mTabHost                =   (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setOnTabChangedListener(listener);
        mTabHost.setup();
        mTabHost.getTabWidget().setVisibility(View.GONE);
        initializeTabs();

        if (savedInstanceState == null) {
            setCurrentTab(0);
        }
    }
    private View createTabView(final int id) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_icon, null);
        ImageView imageView =   (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageDrawable(getResources().getDrawable(id));
        return view;
    }

    public void initializeTabs(){
        /* Setup your tab icons and content views.. Nothing special in this..*/
        TabHost.TabSpec spec    =   mTabHost.newTabSpec(AppConstants.TAB_A);
        mTabHost.setCurrentTab(-3);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator("TAB A");
        mTabHost.addTab(spec);


        spec                    =   mTabHost.newTabSpec(AppConstants.TAB_B);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator("TAB B");
        mTabHost.addTab(spec);

        spec                    =   mTabHost.newTabSpec(AppConstants.TAB_C);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator("TAB C");
        mTabHost.addTab(spec);

        spec                    =   mTabHost.newTabSpec(AppConstants.TAB_D);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator("TAB D");
        mTabHost.addTab(spec);

        spec =   mTabHost.newTabSpec(AppConstants.TAB_E);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator("TAB E");
        mTabHost.addTab(spec);
    }


    /*Comes here when user switch tab, or we do programmatically*/
    TabHost.OnTabChangeListener listener    =   new TabHost.OnTabChangeListener() {
        public void onTabChanged(String tabId) {
        /*Set current tab..*/
            mCurrentTab                     =   tabId;

            if(mStacks.get(tabId).size() == 0){
                if(tabId.equals(AppConstants.TAB_A)){
                    pushFragments(tabId, new AppTabAFirstFragment(), false,true);
                }else if(tabId.equals(AppConstants.TAB_B)){
                    pushFragments(tabId, new AppTabBFirstFragment(), false,true);
                }else if(tabId.equals(AppConstants.TAB_C)){
                    pushFragments(tabId, new AppTabCFirstFragment(), false,true);
                }else if(tabId.equals(AppConstants.TAB_D)){
                    pushFragments(tabId, new AppTabDFirstFragment(), false,true);
                }else if(tabId.equals(AppConstants.TAB_E)){
                    pushFragments(tabId, new AppTabEFirstFragment(), false,true);
                }
            }else {
          /*
           *    We are switching tabs, and target tab is already has atleast one fragment.
           *    No need of animation, no need of stack pushing. Just show the target fragment
           */
                pushFragments(tabId, mStacks.get(tabId).lastElement(), false,false);
            }
        }
    };


    /* Might be useful if we want to switch tab programmatically, from inside any of the fragment.*/
    public void setCurrentTab(int val){
        mTabHost.setCurrentTab(val);
    }


    /*
     *      To add fragment to a tab.
     *  tag             ->  Tab identifier
     *  fragment        ->  Fragment to show, in tab identified by tag
     *  shouldAnimate   ->  should animate transaction. false when we switch tabs, or adding first fragment to a tab
     *                      true when when we are pushing more fragment into navigation stack.
     *  shouldAdd       ->  Should add to fragment navigation stack (mStacks.get(tag)). false when we are switching tabs (except for the first time)
     *                      true in all other cases.
     */
    public void pushFragments(String tag, Fragment fragment,boolean shouldAnimate, boolean shouldAdd){
        if(shouldAdd)
            mStacks.get(tag).push(fragment);
        FragmentManager   manager         =   getSupportFragmentManager();
        FragmentTransaction ft            =   manager.beginTransaction();
        if(shouldAnimate)
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.realtabcontent, fragment);
        ft.commit();
    }


    public void popFragments(){
      /*
       *    Select the second last fragment in current tab's stack..
       *    which will be shown after the fragment transaction given below
       */
        Fragment fragment             =   mStacks.get(mCurrentTab).elementAt(mStacks.get(mCurrentTab).size() - 2);

      /*pop current fragment from stack.. */
        mStacks.get(mCurrentTab).pop();

      /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
        FragmentManager   manager         =   getSupportFragmentManager();
        FragmentTransaction ft            =   manager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.realtabcontent, fragment);
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        } else {
            if(((BaseFragment)mStacks.get(mCurrentTab).lastElement()).onBackPressed() == false){
       		/*
       		 * top fragment in current tab doesn't handles back press, we can do our thing, which is
       		 *
       		 * if current tab has only one fragment in stack, ie first fragment is showing for this tab.
       		 *        finish the activity
       		 * else
       		 *        pop to previous fragment in stack for the same tab
       		 *
       		 */
                if(mStacks.get(mCurrentTab).size() == 1){
                    super.onBackPressed();  // or call finish..
                }else{
                    popFragments();
                }
            }else{
                //do nothing.. fragment already handled back button press.
            }
        }


    }


    /*
     *   Imagine if you wanted to get an image selected using ImagePicker intent to the fragment. Ofcourse I could have created a public function
     *  in that fragment, and called it from the activity. But couldn't resist myself.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mStacks.get(mCurrentTab).size() == 0){
            return;
        }

        /*Now current fragment on screen gets onActivityResult callback..*/
        mStacks.get(mCurrentTab).lastElement().onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Listen Toggle state of navigation Drawer
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
   /* @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        } else {
            finish();
            super.onBackPressed();
        }
    }*/
    // Click Event of navigation drawer item click
    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        setCurrentTab(position);
        drawerList.setItemChecked(position, true);
        setTitle(andoridVeriosnArray[position]);
        drawerLayout.closeDrawer(drawerList);
        // Set Fragment text accordingly
       /* Fragment fragment = new VersionFragment();
        Bundle args = new Bundle();
        args.putString("name", andoridVeriosnArray[position]);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, fragment).commit();

        // Update Title on action bar
        drawerList.setItemChecked(position, true);
        setTitle(andoridVeriosnArray[position]);
        drawerLayout.closeDrawer(drawerList);*/
    }

    @Override
    public void setTitle(CharSequence _title) {
        title = _title;
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        drawerToggle.onConfigurationChanged(newConfig);
    }

}