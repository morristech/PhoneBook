// Generated code from Butter Knife. Do not modify!
package ua.testapp.phonebook.ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.Object;
import java.lang.Override;

public class SettingsActivity$$ViewBinder<T extends SettingsActivity> extends AbstractBaseActivity$$ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = (InnerUnbinder) super.bind(finder, target, source);
    View view;
    view = finder.findRequiredView(source, 2131492972, "field 'rootView'");
    target.rootView = view;
    view = finder.findRequiredView(source, 2131492977, "field 'fabSave' and method 'onClick'");
    target.fabSave = finder.castView(view, 2131492977, "field 'fabSave'");
    unbinder.view2131492977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131492973, "field 'etName'");
    target.etName = finder.castView(view, 2131492973, "field 'etName'");
    view = finder.findRequiredView(source, 2131492974, "field 'etSurname'");
    target.etSurname = finder.castView(view, 2131492974, "field 'etSurname'");
    view = finder.findRequiredView(source, 2131492976, "field 'etPhoneNumber'");
    target.etPhoneNumber = finder.castView(view, 2131492976, "field 'etPhoneNumber'");
    return unbinder;
  }

  @Override
  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends SettingsActivity> extends AbstractBaseActivity$$ViewBinder.InnerUnbinder<T> {
    View view2131492977;

    protected InnerUnbinder(T target) {
      super(target);
    }

    @Override
    protected void unbind(T target) {
      super.unbind(target);
      target.rootView = null;
      view2131492977.setOnClickListener(null);
      target.fabSave = null;
      target.etName = null;
      target.etSurname = null;
      target.etPhoneNumber = null;
    }
  }
}
