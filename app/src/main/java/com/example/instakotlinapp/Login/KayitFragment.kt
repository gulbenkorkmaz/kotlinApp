package com.example.instakotlinapp.Login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.example.instakotlinapp.R
import com.example.instakotlinapp.utils.EventbusDataEvents
import kotlinx.android.synthetic.main.fragment_kayit.*
import kotlinx.android.synthetic.main.fragment_kayit.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class KayitFragment : Fragment() {

    var telNo=""
    var verificationID=""
    var gelenKod=""
    var gelenEmail=""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //içerdeki edittext ve butonlara erişmek için
        var view= inflater.inflate(R.layout.fragment_kayit, container, false)

        view.etAdSoyad.addTextChangedListener(watcher)
        view.etKullancıAdı.addTextChangedListener(watcher)
        view.etSifre.addTextChangedListener(watcher)



        return view
    }

    var watcher :TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if(s!!.length >5 ){
                if(etAdSoyad.text.toString().length>5 && etKullancıAdı.text.toString().length>5 && etSifre.text.toString().length>5){
                    btnGiris.isEnabled=true
                    btnGiris.setTextColor(ContextCompat.getColor(activity!!,R.color.beyaz))
                    btnGiris.setBackgroundResource(R.drawable.register_button_aktif)

                    }else{

                    btnGiris.isEnabled=false
                    btnGiris.setTextColor(ContextCompat.getColor(activity!!,R.color.sonukmavi))
                    btnGiris.setBackgroundResource(R.drawable.register_button)
                }



            }else{
                //kullanıcı 5 karakter uzunluğundan kısa bir veri girerse buton false oluyor.
                btnGiris.isEnabled=false
                btnGiris.setTextColor(ContextCompat.getColor(activity!!,R.color.sonukmavi))
                btnGiris.setBackgroundResource(R.drawable.register_button)


            }

        }


    }

    @Subscribe(sticky = true)
    internal fun onKayitEvent(kayitBilgileri : EventbusDataEvents.KayitBilgileriniGonder){

       if(kayitBilgileri.emailkayit==true){////eğer emailkayıt doğruysa kullanıcı email ile kayıt olur
           gelenEmail=kayitBilgileri.email!!
           Log.e("esma","gelen email"+gelenEmail)
           Toast.makeText(activity,"Gelen Email:"+gelenEmail,Toast.LENGTH_SHORT).show()

       }else{///kullanıcı telefon ile kayıt olur.

           telNo=kayitBilgileri.telNo!!
           verificationID=kayitBilgileri.verificationID!!
           gelenKod=kayitBilgileri.code!!

           Toast.makeText(activity,"Gelen Kod:"+gelenKod+"VerificationID:"+verificationID,Toast.LENGTH_SHORT).show()


       }


    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)

    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }
}
