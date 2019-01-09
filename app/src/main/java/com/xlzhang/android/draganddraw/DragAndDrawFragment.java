package com.xlzhang.android.draganddraw;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DragAndDrawFragment extends Fragment{
    private ArrayList<Box> savedBoxes;
    private BoxDrawingView mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
            savedBoxes = (ArrayList<Box>) savedInstanceState.getSerializable("Boxes");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drag_and_draw, container, false);
        mView = v.findViewById(R.id.boxDrawingView);
        if(savedBoxes != null)
            mView.setBoxes(savedBoxes);
        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Boxes", mView.getBoxes());
    }
}
