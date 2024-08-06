package fpoly.ph45160.thithu_and103_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XeMayApdater extends RecyclerView.Adapter<XeMayApdater.XeMayViewHolder> {

    private List<XeMayModel> listXeMay;
    private Context context;
    ApiService apiService;



    public XeMayApdater(List<XeMayModel> listXeMay, Context context) {
        this.listXeMay = listXeMay;
        this.context = context;
    }

    @NonNull
    @Override
    public XeMayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xe, parent, false);
        return new XeMayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XeMayViewHolder holder, @SuppressLint("RecyclerView") int position) {
        XeMayModel xeMay = listXeMay.get(position);
        holder.tv_TenXe.setText("Tên Xe: "+xeMay.getTen_xe_ph45160());
        holder.tv_MauSac.setText("Màu Sắc: "+xeMay.getMau_sac_ph45160());
        holder.tv_MoTa.setText("Mô Tả: "+xeMay.getMo_ta_ph45160());
        holder.tv_Gia.setText("Giá Bán : "+String.valueOf(xeMay.getGia_ban_ph45160()));

        apiService = RetrofitClient.getInstance().create(ApiService.class);


        holder.btnEdit.setOnClickListener(v -> {
            showEditDialog(xeMay, position, context);
        });


        holder.btnDelete.setOnClickListener(v -> {
            showDeleteConfirmationDialog(xeMay, position);
        });

        holder.tv_TenXe.setOnClickListener(v -> {
            showDetailDialog(xeMay);
        });

    }

    private void showDeleteConfirmationDialog(XeMayModel xeMay, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa xe này không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            Call<Void> deleteCall = apiService.deleteXeMay(xeMay.get_id());
            deleteCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        listXeMay.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, listXeMay.size());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API_ERROR", "Lỗi: " + t.getMessage());
                }
            });
        });

        builder.setNegativeButton("Không", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return listXeMay.size();
    }

    public void addXeMay(XeMayModel xeMay) {
        listXeMay.add(xeMay);
        notifyItemInserted(listXeMay.size() - 1);
    }

    private void showDetailDialog(XeMayModel xeMay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_detail, null);
        builder.setView(dialogView);

        TextView tvTenXe = dialogView.findViewById(R.id.tv_detail_ten_xe);
        TextView tvMauSac = dialogView.findViewById(R.id.tv_detail_mau_sac);
        TextView tvGiaBan = dialogView.findViewById(R.id.tv_detail_gia_ban);
        TextView tvMoTa = dialogView.findViewById(R.id.tv_detail_mo_ta);
        ImageView ivHinhAnh = dialogView.findViewById(R.id.iv_detail_hinh_anh);

        tvTenXe.setText("Tên Xe: " + xeMay.getTen_xe_ph45160());
        tvMauSac.setText("Màu Sắc: " + xeMay.getMau_sac_ph45160());
        tvGiaBan.setText("Giá Bán: " + String.valueOf(xeMay.getGia_ban_ph45160()));
        tvMoTa.setText("Mô Tả: " + xeMay.getMo_ta_ph45160());

        // Load image from URL (assuming you're using a library like Picasso or Glide)
        Glide.with(context).load(xeMay.getHinh_anh_ph45160()).into(ivHinhAnh);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void updateXeMay(String id, XeMayModel xeMayModel, int position) {
        Call<XeMayModel> call = apiService.updateXeMay(id, xeMayModel);
        call.enqueue(new Callback<XeMayModel>() {
            @Override
            public void onResponse(Call<XeMayModel> call, Response<XeMayModel> response) {
                if (response.isSuccessful()) {
                    // Cập nhật dữ liệu trong danh sách và adapter
                    listXeMay.set(position, response.body());
                    notifyItemChanged(position);
                }
            }

            @Override
            public void onFailure(Call<XeMayModel> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi: " + t.getMessage());
            }
        });
    }

    private void showEditDialog(XeMayModel xeMay, int position, Context context1) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context1);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_add, parent, false);
        View dialogView = LayoutInflater.from(context1).inflate(R.layout.dialog_add, null);
        builder.setView(dialogView);

        EditText etTenXe = dialogView.findViewById(R.id.et_ten_xe);
        EditText etMauSac = dialogView.findViewById(R.id.et_mau_sac);
        EditText etGiaBan = dialogView.findViewById(R.id.et_gia_ban);
        EditText etMoTa = dialogView.findViewById(R.id.et_mo_ta);
        EditText etHinhAnh = dialogView.findViewById(R.id.et_hinh_anh);
        Button btnSubmit = dialogView.findViewById(R.id.btn_submit);

        // Điền dữ liệu vào dialog
        etTenXe.setText(xeMay.getTen_xe_ph45160());
        etMauSac.setText(xeMay.getMau_sac_ph45160());
        etGiaBan.setText(String.valueOf(xeMay.getGia_ban_ph45160()));
        etMoTa.setText(xeMay.getMo_ta_ph45160());
        etHinhAnh.setText(xeMay.getHinh_anh_ph45160());

        AlertDialog dialog = builder.create();

        btnSubmit.setOnClickListener(v -> {
            String tenXe = etTenXe.getText().toString();
            String mauSac = etMauSac.getText().toString();
            double giaBan = Double.parseDouble(etGiaBan.getText().toString());
            String moTa = etMoTa.getText().toString();
            String hinhAnh = etHinhAnh.getText().toString();

            XeMayModel updatedXeMay = new XeMayModel(tenXe, mauSac, giaBan, moTa, hinhAnh);
            updateXeMay(xeMay.get_id(), updatedXeMay, position);
            dialog.dismiss();
        });

        dialog.show();
    }



    public static class XeMayViewHolder extends RecyclerView.ViewHolder {
        TextView tv_TenXe, tv_MauSac, tv_MoTa, tv_Gia;
        ImageView btnDelete, btnEdit;
        public XeMayViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TenXe = itemView.findViewById(R.id.txt_ten_xe);
            tv_Gia = itemView.findViewById(R.id.txt_gia_ban);
            tv_MauSac = itemView.findViewById(R.id.txt_mau_sac);
            tv_MoTa = itemView.findViewById(R.id.txt_mo_ta);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }
    }
}
