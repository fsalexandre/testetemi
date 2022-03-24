// Generated by view binder compiler. Do not edit!
package com.bsc.protonbusmodscom.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.bsc.protonbusmodscom.R;
import com.google.android.material.slider.Slider;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDisplayRouteBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAddCamada;

  @NonNull
  public final ImageButton btnChoiceColor;

  @NonNull
  public final ImageButton btnDown;

  @NonNull
  public final ImageButton btnLeft;

  @NonNull
  public final ImageButton btnMinus;

  @NonNull
  public final ImageButton btnPlus;

  @NonNull
  public final ImageButton btnRight;

  @NonNull
  public final ImageButton btnSaveGallery;

  @NonNull
  public final ImageButton btnUP;

  @NonNull
  public final Spinner fontSpinner;

  @NonNull
  public final ImageView imgDisplay;

  @NonNull
  public final ImageView imgDisplayCompile;

  @NonNull
  public final RecyclerView rvListObjectsEditor;

  @NonNull
  public final Slider sliderfontsize;

  @NonNull
  public final EditText txtDisplayRoute;

  private FragmentDisplayRouteBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnAddCamada, @NonNull ImageButton btnChoiceColor,
      @NonNull ImageButton btnDown, @NonNull ImageButton btnLeft, @NonNull ImageButton btnMinus,
      @NonNull ImageButton btnPlus, @NonNull ImageButton btnRight,
      @NonNull ImageButton btnSaveGallery, @NonNull ImageButton btnUP, @NonNull Spinner fontSpinner,
      @NonNull ImageView imgDisplay, @NonNull ImageView imgDisplayCompile,
      @NonNull RecyclerView rvListObjectsEditor, @NonNull Slider sliderfontsize,
      @NonNull EditText txtDisplayRoute) {
    this.rootView = rootView;
    this.btnAddCamada = btnAddCamada;
    this.btnChoiceColor = btnChoiceColor;
    this.btnDown = btnDown;
    this.btnLeft = btnLeft;
    this.btnMinus = btnMinus;
    this.btnPlus = btnPlus;
    this.btnRight = btnRight;
    this.btnSaveGallery = btnSaveGallery;
    this.btnUP = btnUP;
    this.fontSpinner = fontSpinner;
    this.imgDisplay = imgDisplay;
    this.imgDisplayCompile = imgDisplayCompile;
    this.rvListObjectsEditor = rvListObjectsEditor;
    this.sliderfontsize = sliderfontsize;
    this.txtDisplayRoute = txtDisplayRoute;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDisplayRouteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDisplayRouteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_display_route, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDisplayRouteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAddCamada;
      Button btnAddCamada = ViewBindings.findChildViewById(rootView, id);
      if (btnAddCamada == null) {
        break missingId;
      }

      id = R.id.btnChoiceColor;
      ImageButton btnChoiceColor = ViewBindings.findChildViewById(rootView, id);
      if (btnChoiceColor == null) {
        break missingId;
      }

      id = R.id.btnDown;
      ImageButton btnDown = ViewBindings.findChildViewById(rootView, id);
      if (btnDown == null) {
        break missingId;
      }

      id = R.id.btnLeft;
      ImageButton btnLeft = ViewBindings.findChildViewById(rootView, id);
      if (btnLeft == null) {
        break missingId;
      }

      id = R.id.btnMinus;
      ImageButton btnMinus = ViewBindings.findChildViewById(rootView, id);
      if (btnMinus == null) {
        break missingId;
      }

      id = R.id.btnPlus;
      ImageButton btnPlus = ViewBindings.findChildViewById(rootView, id);
      if (btnPlus == null) {
        break missingId;
      }

      id = R.id.btnRight;
      ImageButton btnRight = ViewBindings.findChildViewById(rootView, id);
      if (btnRight == null) {
        break missingId;
      }

      id = R.id.btnSaveGallery;
      ImageButton btnSaveGallery = ViewBindings.findChildViewById(rootView, id);
      if (btnSaveGallery == null) {
        break missingId;
      }

      id = R.id.btnUP;
      ImageButton btnUP = ViewBindings.findChildViewById(rootView, id);
      if (btnUP == null) {
        break missingId;
      }

      id = R.id.fontSpinner;
      Spinner fontSpinner = ViewBindings.findChildViewById(rootView, id);
      if (fontSpinner == null) {
        break missingId;
      }

      id = R.id.imgDisplay;
      ImageView imgDisplay = ViewBindings.findChildViewById(rootView, id);
      if (imgDisplay == null) {
        break missingId;
      }

      id = R.id.imgDisplayCompile;
      ImageView imgDisplayCompile = ViewBindings.findChildViewById(rootView, id);
      if (imgDisplayCompile == null) {
        break missingId;
      }

      id = R.id.rvListObjectsEditor;
      RecyclerView rvListObjectsEditor = ViewBindings.findChildViewById(rootView, id);
      if (rvListObjectsEditor == null) {
        break missingId;
      }

      id = R.id.sliderfontsize;
      Slider sliderfontsize = ViewBindings.findChildViewById(rootView, id);
      if (sliderfontsize == null) {
        break missingId;
      }

      id = R.id.txtDisplayRoute;
      EditText txtDisplayRoute = ViewBindings.findChildViewById(rootView, id);
      if (txtDisplayRoute == null) {
        break missingId;
      }

      return new FragmentDisplayRouteBinding((ConstraintLayout) rootView, btnAddCamada,
          btnChoiceColor, btnDown, btnLeft, btnMinus, btnPlus, btnRight, btnSaveGallery, btnUP,
          fontSpinner, imgDisplay, imgDisplayCompile, rvListObjectsEditor, sliderfontsize,
          txtDisplayRoute);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
