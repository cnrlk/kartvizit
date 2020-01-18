package com.caner.newproject.service;

import android.os.Build;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class CardsResource {

    private static final int MAX_DOWNLOAD_LIMIT_BYTES = 1024 * 1024;

    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public Task<ListResult> listCards() {
        return storage.getReference("images").listAll();
    }

    // TODO: buraya dosya isminin de gelmesi gerekiyor ki upload location ile upload edebilelim.
    public UploadTask uploadCard(String card) {
        return storage.getReference("test.png").putBytes(card.getBytes());
    }

    public Task<byte[]> downloadCard(StorageReference reference) {
        return reference.getBytes(MAX_DOWNLOAD_LIMIT_BYTES);
    }
}
