package com.caner.newproject.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.caner.newproject.BR;
import com.caner.newproject.R;
import com.caner.newproject.databinding.ActivityDashboardBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Observer;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class DashboardActivity extends Activity {

    private DashboardViewModel viewModel;
    private ActivityDashboardBinding binding;

    private LinearLayoutManager layoutManager;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        viewModel = new DashboardViewModel();
        binding.setViewModel(viewModel);

        layoutManager = new LinearLayoutManager(this);

        //viewModel.getCards();
        //viewModel.uploadCard("sample_card");

        viewModel.listCards().addOnSuccessListener(listResult -> {
            List<String> cards = new ArrayList<>();
            for (StorageReference reference : listResult.getItems()) {
                viewModel.downloadCard(reference).
                        addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                cards.add(Base64.getEncoder()
                                        .encodeToString(bytes));
                                viewModel.setCards(cards);
                            }
                        });
            }
        });
        viewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == com.caner.newproject.BR.cards) {
                    binding.cardsRecycler.setLayoutManager(layoutManager);
                    binding.cardsRecycler.setAdapter(new CardsRecyclerAdapter(viewModel.getCards()));
                }
            }
        });
        List<String> dummy = new ArrayList<>();
        dummy.add("asdgsa");
        dummy.add("asfha");
        CardsRecyclerAdapter adapter = new CardsRecyclerAdapter(dummy);
        //binding.cardsRecycler.setAdapter(adapter);
    }
}
