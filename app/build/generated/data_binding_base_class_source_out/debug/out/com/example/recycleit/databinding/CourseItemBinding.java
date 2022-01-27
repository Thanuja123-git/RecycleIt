// Generated by view binder compiler. Do not edit!
package com.example.recycleit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.recycleit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CourseItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView idCVCOurseItem;

  @NonNull
  public final TextView idTVCode;

  @NonNull
  public final TextView idTVCompanyname;

  @NonNull
  public final TextView idTVdateofcoll;

  private CourseItemBinding(@NonNull CardView rootView, @NonNull CardView idCVCOurseItem,
      @NonNull TextView idTVCode, @NonNull TextView idTVCompanyname,
      @NonNull TextView idTVdateofcoll) {
    this.rootView = rootView;
    this.idCVCOurseItem = idCVCOurseItem;
    this.idTVCode = idTVCode;
    this.idTVCompanyname = idTVCompanyname;
    this.idTVdateofcoll = idTVdateofcoll;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CourseItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CourseItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.course_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CourseItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView idCVCOurseItem = (CardView) rootView;

      id = R.id.idTVCode;
      TextView idTVCode = ViewBindings.findChildViewById(rootView, id);
      if (idTVCode == null) {
        break missingId;
      }

      id = R.id.idTVCompanyname;
      TextView idTVCompanyname = ViewBindings.findChildViewById(rootView, id);
      if (idTVCompanyname == null) {
        break missingId;
      }

      id = R.id.idTVdateofcoll;
      TextView idTVdateofcoll = ViewBindings.findChildViewById(rootView, id);
      if (idTVdateofcoll == null) {
        break missingId;
      }

      return new CourseItemBinding((CardView) rootView, idCVCOurseItem, idTVCode, idTVCompanyname,
          idTVdateofcoll);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
