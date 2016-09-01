// Generated code from Butter Knife. Do not modify!
package ua.testapp.phonebook.ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.Object;
import java.lang.Override;

public class LoginActivity$$ViewBinder<T extends LoginActivity> extends AbstractBaseActivity$$ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = (InnerUnbinder) super.bind(finder, target, source);
    View view;
    view = finder.findRequiredView(source, 2131492972, "field 'rootView'");
    target.rootView = view;
    view = finder.findRequiredView(source, 2131492983, "field 'tvLogo'");
    target.tvLogo = finder.castView(view, 2131492983, "field 'tvLogo'");
    view = finder.findRequiredView(source, 2131492985, "field 'etLogin'");
    target.etLogin = finder.castView(view, 2131492985, "field 'etLogin'");
    view = finder.findRequiredView(source, 2131492986, "field 'etPassword'");
    target.etPassword = finder.castView(view, 2131492986, "field 'etPassword'");
    view = finder.findRequiredView(source, 2131492984, "field 'llInputContainer'");
    target.llInputContainer = finder.castView(view, 2131492984, "field 'llInputContainer'");
    view = finder.findRequiredView(source, 2131492987, "field 'tvSignIn' and method 'onClick'");
    target.tvSignIn = finder.castView(view, 2131492987, "field 'tvSignIn'");
    unbinder.view2131492987 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    return unbinder;
  }

  @Override
  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LoginActivity> extends AbstractBaseActivity$$ViewBinder.InnerUnbinder<T> {
    View view2131492987;

    protected InnerUnbinder(T target) {
      super(target);
    }

    @Override
    protected void unbind(T target) {
      super.unbind(target);
      target.rootView = null;
      target.tvLogo = null;
      target.etLogin = null;
      target.etPassword = null;
      target.llInputContainer = null;
      view2131492987.setOnClickListener(null);
      target.tvSignIn = null;
    }
  }
}
