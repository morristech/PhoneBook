// Generated code from Butter Knife. Do not modify!
package ua.testapp.phonebook.ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.Object;
import java.lang.Override;

public class CreateContactActivity$$ViewBinder<T extends CreateContactActivity> extends AbstractBaseActivity$$ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = (InnerUnbinder) super.bind(finder, target, source);
    View view;
    view = finder.findRequiredView(source, 2131492972, "field 'rootView'");
    target.rootView = view;
    view = finder.findRequiredView(source, 2131492975, "field 'etName'");
    target.etName = finder.castView(view, 2131492975, "field 'etName'");
    view = finder.findRequiredView(source, 2131492976, "field 'etSurname'");
    target.etSurname = finder.castView(view, 2131492976, "field 'etSurname'");
    view = finder.findRequiredView(source, 2131492978, "field 'etPhoneNumber'");
    target.etPhoneNumber = finder.castView(view, 2131492978, "field 'etPhoneNumber'");
    view = finder.findRequiredView(source, 2131492979, "field 'etPosition'");
    target.etPosition = finder.castView(view, 2131492979, "field 'etPosition'");
    view = finder.findRequiredView(source, 2131492980, "field 'etEmail'");
    target.etEmail = finder.castView(view, 2131492980, "field 'etEmail'");
    view = finder.findRequiredView(source, 2131492981, "field 'etContactLink'");
    target.etContactLink = finder.castView(view, 2131492981, "field 'etContactLink'");
    view = finder.findRequiredView(source, 2131492974, "field 'ivPhoto'");
    target.ivPhoto = finder.castView(view, 2131492974, "field 'ivPhoto'");
    return unbinder;
  }

  @Override
  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends CreateContactActivity> extends AbstractBaseActivity$$ViewBinder.InnerUnbinder<T> {
    protected InnerUnbinder(T target) {
      super(target);
    }

    @Override
    protected void unbind(T target) {
      super.unbind(target);
      target.rootView = null;
      target.etName = null;
      target.etSurname = null;
      target.etPhoneNumber = null;
      target.etPosition = null;
      target.etEmail = null;
      target.etContactLink = null;
      target.ivPhoto = null;
    }
  }
}
