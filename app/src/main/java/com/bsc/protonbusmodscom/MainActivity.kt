package com.bsc.protonbusmodscom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bsc.protonbusmodscom.ui.displayroute.DisplayRouteFragment
import com.bsc.protonbusmodscom.ui.main.MainFragment
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.main_activity.*
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.*


class MainActivity : AppCompatActivity() {

    private lateinit var menudev: Menu
    private var iconMenu=IconStatus.Youtube

    enum class IconStatus {
        Youtube, AppIcon
    }

    enum class ScreenStatus {
        DISPLAY_ROUTE_SCREEN, MAIN_SCREEN
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mnumods, menu)
        this.menudev= menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnYoutube -> openExternalYoutube()
            R.id.btnPrivacyInfo -> showPrivacyPolicy()
            R.id.btnDisplayRoute -> openDisplayRouteGen()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openDisplayRouteGen(){






        val myFragment: DisplayRouteFragment? =
            supportFragmentManager.findFragmentByTag(ScreenStatus.DISPLAY_ROUTE_SCREEN.name) as DisplayRouteFragment?
        if (myFragment != null && myFragment.isVisible) {
            menudev.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.displayicon)
            iconMenu=IconStatus.Youtube

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(), ScreenStatus.MAIN_SCREEN.name)
                .commitNow()
        }else{
            menudev.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.modsicon)
            iconMenu=IconStatus.AppIcon
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DisplayRouteFragment.newInstance(), ScreenStatus.DISPLAY_ROUTE_SCREEN.name)
                .commitNow()
        }

    }





    private fun openExternalYoutube() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/bussimulationchannel?sub_confirmation=1"))
        startActivity(browserIntent)
    }

    private fun showPrivacyPolicy(){
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage("PRIVACY POLICY\n" +
                "\n" +
                "The Application does not collect any personal data of the user such as name, photographs or location.\n" +
                "\n" +
                "Consequently, the application does not share any personal information with other entities or third parties.\n" +
                "\n" +
                "The Permission granted at the beginning of the application refers to recording the Download of the Mod in the Downloads folder of your device.\n" +
                "\n" +
                "We allow third-party companies to collect certain anonymous information when you visit our application. These companies may use anonymous information, such as the Google Advertising ID, device type and version, browsing activity, location and other technical data related to your device, in order to provide ads.")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "OK"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }


}
