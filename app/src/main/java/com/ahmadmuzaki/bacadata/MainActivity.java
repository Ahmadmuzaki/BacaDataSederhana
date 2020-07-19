package com.ahmadmuzaki.bacadata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText editNama, editTanggal, editAlamat, editJenisKelamin, editUsername, editPassword;
    Button btn_addData, btn_lihatData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        editNama = findViewById(R.id.editNama);
        editTanggal = findViewById(R.id.editTanggalLahir);
        editAlamat = findViewById(R.id.editAlamat);
        editJenisKelamin = findViewById(R.id.editJenisKelamin);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btn_addData = findViewById(R.id.tambahData);
        btn_lihatData = findViewById(R.id.lihatData);
        addDataButton();
        lihatDataButton();

    }

    public void addDataButton(){
        btn_addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEditNama = editNama.getText().toString();
                String strEditTanggal = editTanggal.getText().toString();
                String strEditAlamat = editAlamat.getText().toString();
                String strEditJenisKelamin = editJenisKelamin.getText().toString();
                String strEditUsername= editUsername.getText().toString();
                String strEditPassword = editPassword.getText().toString();
                boolean dataDitambah = databaseHelper.insertData(strEditNama, strEditTanggal, strEditAlamat, strEditJenisKelamin, strEditUsername, strEditPassword);

                if (dataDitambah){
                    Toast.makeText(MainActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void lihatDataButton(){
        btn_lihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = databaseHelper.getAllData();

                if (result.getCount() == 0){
                    //Jika data tidak ada makan akan menampilkan pesan
                    tampilPesan("Error", "Tidak ditemukan Data Member");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("Kode_member: "+ result.getString(0) + "\n");
                    buffer.append("Nama: "+ result.getString(1) + "\n");
                    buffer.append("Tanggal_lahir: "+ result.getString(2) + "\n");
                    buffer.append("Alamat: "+ result.getString(3) + "\n");
                    buffer.append("Jenis_kelamin: "+ result.getString(4) + "\n");
                    buffer.append("Username: "+ result.getString(5) + "\n\n");
                }

                //Tampil semua Data
                tampilPesan("Data Member", buffer.toString());
            }
        });
    }

    public void tampilPesan(String judul, String pesan){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.show();
    }
}