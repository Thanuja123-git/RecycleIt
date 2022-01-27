// Generated by view binder compiler. Do not edit!
package com.example.recycleit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.recycleit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterDateBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText edtcode;

  @NonNull
  public final EditText edtcompanyname;

  @NonNull
  public final EditText edtdateofcoll;

  @NonNull
  public final Button idBtnSubmit;

  private ActivityRegisterDateBinding(@NonNull LinearLayout rootView, @NonNull EditText edtcode,
      @NonNull EditText edtcompanyname, @NonNull EditText edtdateofcoll,
      @NonNull Button idBtnSubmit) {
    this.rootView = rootView;
    this.edtcode = edtcode;
    this.edtcompanyname = edtcompanyname;
    this.edtdateofcoll = edtdateofcoll;
    this.idBtnSubmit = idBtnSubmit;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterDateBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterDateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register_date, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterDateBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edtcode;
      EditText edtcode = ViewBindings.findChildViewById(rootView, id);
      if (edtcode == null) {
        break missingId;
      }

      id = R.id.edtcompanyname;
      EditText edtcompanyname = ViewBindings.findChildViewById(rootView, id);
      if (edtcompanyname == null) {
        break missingId;
      }

      id = R.id.edtdateofcoll;
      EditText edtdateofcoll = ViewBindings.findChildViewById(rootView, id);
      if (edtdateofcoll == null) {
        break missingId;
      }

      id = R.id.idBtnSubmit;
      Button idBtnSubmit = ViewBindings.findChildViewById(rootView, id);
      if (idBtnSubmit == null) {
        break missingId;
      }

      return new ActivityRegisterDateBinding((LinearLayout) rootView, edtcode, edtcompanyname,
          edtdateofcoll, idBtnSubmit);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
