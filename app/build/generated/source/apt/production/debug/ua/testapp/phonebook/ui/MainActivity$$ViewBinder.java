// Generated code from Butter Knife. Do not modify!
package ua.testapp.phonebook.ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewBinder<T extends MainActivity> extends AbstractBaseActivity$$ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = (InnerUnbinder) super.bind(finder, target, source);
    View view;
    view = finder.findRequiredView(source, 2131492991, "field 'fabAddContact' and method 'onClick'");
    target.fabAddContact = finder.castView(view, 2131492991, "field 'fabAddContact'");
    unbinder.view2131492991 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131492988, "field 'drawer'");
    target.drawer = finder.castView(view, 2131492988, "field 'drawer'");
    view = finder.findRequiredView(source, 2131492989, "field 'navigationView'");
    target.navigationView = finder.castView(view, 2131492989, "field 'navigationView'");
    view = finder.findRequiredView(source, 2131492995, "field 'swipeRefreshLayout'");
    target.swipeRefreshLayout = finder.castView(view, 2131492995, "field 'swipeRefreshLayout'");
    view = finder.findRequiredView(source, 2131492997, "field 'rvContacts'");
    target.rvContacts = finder.castView(view, 2131492997, "field 'rvContacts'");
    view = finder.findRequiredView(source, 2131492993, "field 'progressBar'");
    target.progressBar = finder.castView(view, 2131492993, "field 'progressBar'");
    view = finder.findRequiredView(source, 2131492994, "field 'flContainer'");
    target.flContainer = finder.castView(view, 2131492994, "field 'flContainer'");
    view = finder.findRequiredView(source, 2131492992, "field 'tvEmptyText'");
    target.tvEmptyText = finder.castView(view, 2131492992, "field 'tvEmptyText'");
    return unbinder;
  }

  @Override
  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MainActivity> extends AbstractBaseActivity$$ViewBinder.InnerUnbinder<T> {
    View view2131492991;

    protected InnerUnbinder(T target) {
      super(target);
    }

    @Override
    protected void unbind(T target) {
      super.unbind(target);
      view2131492991.setOnClickListener(null);
      target.fabAddContact = null;
      target.drawer = null;
      target.navigationView = null;
      target.swipeRefreshLayout = null;
      target.rvContacts = null;
      target.progressBar = null;
      target.flContainer = null;
      target.tvEmptyText = null;
    }
  }
}
