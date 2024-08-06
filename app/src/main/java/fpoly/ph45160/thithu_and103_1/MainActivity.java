package fpoly.ph45160.thithu_and103_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    XeMayApdater xeMayAdapter;
    FloatingActionButton btn_add;
    private List<XeMayModel> listXeMay;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_xemay);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_add = findViewById(R.id.btn_add);

        apiService = RetrofitClient.getInstance().create(ApiService.class);
        loadXeMays();

        btn_add.setOnClickListener(v -> {
            showAddDialog();
        });



    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(dialogView);

        EditText etTenXe = dialogView.findViewById(R.id.et_ten_xe);
        EditText etMauSac = dialogView.findViewById(R.id.et_mau_sac);
        EditText etGiaBan = dialogView.findViewById(R.id.et_gia_ban);
        EditText etMoTa = dialogView.findViewById(R.id.et_mo_ta);
        EditText etHinhAnh = dialogView.findViewById(R.id.et_hinh_anh);
        Button btnSubmit = dialogView.findViewById(R.id.btn_submit);

        AlertDialog dialog = builder.create();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenXe = etTenXe.getText().toString();
                String mauSac = etMauSac.getText().toString();
                double giaBan = Double.parseDouble(etGiaBan.getText().toString());
                String moTa = etMoTa.getText().toString();
                String hinhAnh = etHinhAnh.getText().toString();

                XeMayModel newXeMay = new XeMayModel(tenXe, mauSac, giaBan, moTa, hinhAnh);
                addXeMay(newXeMay);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void addXeMay(XeMayModel xeMayModel) {
        Call<XeMayModel> call = apiService.createXeMay(xeMayModel);
        call.enqueue(new Callback<XeMayModel>() {
            @Override
            public void onResponse(Call<XeMayModel> call, Response<XeMayModel> response) {
                if (response.isSuccessful()) {
                    // Thêm dữ liệu mới vào danh sách và cập nhật adapter
                    xeMayAdapter.addXeMay(response.body());
                }
            }

            @Override
            public void onFailure(Call<XeMayModel> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
            }
        });
    }

    private void loadXeMays() {
        Call<List<XeMayModel>> call = apiService.getXeMay();
        call.enqueue(new Callback<List<XeMayModel>>() {
            @Override
            public void onResponse(Call<List<XeMayModel>> call, Response<List<XeMayModel>> response) {
                if (response.isSuccessful()) {
                    List<XeMayModel> xeMayList = response.body();
                    xeMayAdapter = new XeMayApdater(xeMayList, (Activity) MainActivity.this);
                    recyclerView.setAdapter(xeMayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<XeMayModel>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
            }
        });
    }



}
