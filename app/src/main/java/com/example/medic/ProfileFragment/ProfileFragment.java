package com.example.medic.ProfileFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.medic.CreateCard.CreateCardActivity;
import com.example.medic.R;
import com.example.medic.common.CardPatient;
import com.example.medic.common.DBHandlerMedic;
import com.example.medic.common.ProfilesModelResponse;
import com.example.medic.common.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements TextWatcher {


    private static final String MY_SETTINGS = "my_settings_CreateCard";
    DBHandlerMedic dbHandlerMedic;
    private Context context;
    Spinner spinner_gender;
    ImageView profile_image;
    Uri selectedImageUri;
    CardPatient cardPatient;
    Button btn_create_card;
    List<CardPatient> profiles;

    Integer profileId;
    EditText editText_first_name,editText_lastname,editText_middle_name,editText_date_birthday;
    private static final String SETTING_ACCESS= "setting_refresh";
    private static final String ACCESS = "refresh";
    SharedPreferences sharedPreferences;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //получаем был ли пропущен экран создания карточки, если да, переход на экран создания
        SharedPreferences sp = getContext().getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        boolean hasSkipped = sp.getBoolean("hasSkipped",true);
        if(hasSkipped){
            Intent i = new Intent(getContext(), CreateCardActivity.class);
            startActivity(i);
            getActivity().finish();
        }
        context = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        editText_first_name = view.findViewById(R.id.editText_first_name);
        editText_lastname = view.findViewById(R.id.editText_lastname);
        editText_middle_name = view.findViewById(R.id.editText_middle_name);
        editText_date_birthday = view.findViewById(R.id.editText_date_birthday);
        spinner_gender = view.findViewById(R.id.spinner_gender);
        btn_create_card = view.findViewById(R.id.btn_create_card);
        profile_image = view.findViewById(R.id.profile_image);

        //присваиваем выпадающему списку с выбором пола адаптер
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(context, R.array.list_gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapter);


        //инизиализируем вспомогательный класс для работы с БД
        dbHandlerMedic = new DBHandlerMedic(context);



        sharedPreferences = context.getSharedPreferences(SETTING_ACCESS,Context.MODE_PRIVATE);
        String access = "Bearer "+sharedPreferences.getString(ACCESS,"");

        Runnable  runnable = new Runnable() {
            @Override
            public void run() {
                profileId = dbHandlerMedic.getCardPatientId();
                RetrofitClient.getRetrofitClient().GetProfile(access,profileId).enqueue(new Callback<CardPatient>() {
                    @Override
                    public void onResponse(Call<CardPatient> call, Response<CardPatient> response) {
                        if (response.isSuccessful()){
                            cardPatient = response.body();
                            editText_first_name.setText(cardPatient.getFirst_name());
                            editText_lastname.setText(cardPatient.getLast_name());
                            editText_middle_name.setText(cardPatient.getMiddle_name());
                            editText_date_birthday.setText(cardPatient.getDate_of_birth());
                            if(cardPatient.getImage()!=null){
                                Glide.with(context).load(cardPatient.getImage()).into(profile_image);
                            }
                            spinner_gender.setSelection(Integer.valueOf(cardPatient.getPol()),false);
                            btn_create_card.setEnabled(false);
                            spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    btn_create_card.setEnabled(true);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }
                        else {
                            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CardPatient> call, Throwable t) {

                    }
                });
            }
        };
        Thread thread = new Thread(runnable);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //если пользователь авторизирован на нескольких устройствах
                    RetrofitClient.getRetrofitClient().getAllCardPatientUser(access).enqueue(new Callback<ProfilesModelResponse>() {
                        @Override
                        public void onResponse(Call<ProfilesModelResponse> call, Response<ProfilesModelResponse> response) {
                            profiles  = response.body().getResults();
                            for (CardPatient cardPatient1:profiles){
                                if (!dbHandlerMedic.PatientExists(cardPatient1.getId())){
                                    dbHandlerMedic.addCardPatient(cardPatient1);
                                }
                            }
                            thread.start();
                        }

                        @Override
                        public void onFailure(Call<ProfilesModelResponse> call, Throwable t) {

                        }
                    });

                }catch (Exception ex){
                }
            }
        }).start();


        editText_first_name.addTextChangedListener(this);
        editText_lastname.addTextChangedListener(this);
        editText_middle_name.addTextChangedListener(this);
        editText_date_birthday.addTextChangedListener(this);

        btn_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardPatient cardPatient1 = new CardPatient(editText_first_name.getText().toString(),
                        editText_lastname.getText().toString(),
                        editText_middle_name.getText().toString(),
                        editText_date_birthday.getText().toString(),
                        String.valueOf(spinner_gender.getSelectedItemId()));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d(String.valueOf(profileId),String.valueOf(profileId));
                            RetrofitClient.getRetrofitClient().UpdateProfile(access,profileId,cardPatient1).
                                    enqueue(new Callback<CardPatient>() {
                                        @Override
                                        public void onResponse(Call<CardPatient> call, Response<CardPatient> response) {
                                            //обновляем в локальной бд
                                          dbHandlerMedic.updatePatient(response.body());
                                        }

                                        @Override
                                        public void onFailure(Call<CardPatient> call, Throwable t) {

                                        }
                                    });
                        }catch (Exception e){

                        }
                    }
                }).start();
                btn_create_card.setEnabled(false);
            }
        });
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
            return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //если хотя бы одно из полей было изменено, то кнопка сохранить доступна
        if(editText_first_name.getText().toString().equals(cardPatient.getFirst_name())
                &&editText_middle_name.getText().toString().equals(cardPatient.getMiddle_name())
                &&editText_lastname.getText().toString().equals(cardPatient.getLast_name())
                &&editText_date_birthday.getText().toString().equals(cardPatient.getDate_of_birth())){
            btn_create_card.setEnabled(false);
        }else {
            btn_create_card.setEnabled(true);
        } if(editText_first_name.getText().toString().length()==0 ||
              editText_middle_name.getText().toString().length()==0
                ||editText_lastname.getText().toString().length()==0
                ||editText_date_birthday.getText().toString().length()==0){
            btn_create_card.setEnabled(false);
        }else {
            btn_create_card.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null
                            && data.getData() != null) {
                        selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    context.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        profile_image.setImageBitmap(
                                selectedImageBitmap);
                    }
                }
            });
    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }



}