package com.example.ereaapp.UI.Listenres;

import com.example.ereaapp.Module.Country;

public interface BordersDBListener {

    void getBordersListSuccess(Country Country);

    void getBordersListFailure();
}

