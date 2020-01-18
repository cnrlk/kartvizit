package com.caner.newproject.dashboard;

import android.os.Build;

import com.caner.newproject.BR;
import com.caner.newproject.service.CardsResource;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Base64;
import java.util.List;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class DashboardViewModel extends BaseObservable {

    private CardsResource cardsResource = new CardsResource();

    @Bindable
    private List<String> cards = null;


    public Task<ListResult> listCards() {
        return cardsResource.listCards();
    }

    public Task<byte[]> downloadCard(StorageReference reference) {
        return cardsResource.downloadCard(reference);
    }

    public int getTotalCards() {
        return cards.size();
    }

    public UploadTask uploadCard(String card) {
        return cardsResource.uploadCard(card);
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
        this.notifyPropertyChanged(com.caner.newproject.BR.cards);
    }

    public List<String> getCards() {
        return cards;
    }
}
