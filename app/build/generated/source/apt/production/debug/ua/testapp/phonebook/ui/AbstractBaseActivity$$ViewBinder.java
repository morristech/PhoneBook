// Generated code from Butter Knife. Do not modify!
package ua.testapp.phonebook.ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AbstractBaseActivity$$ViewBinder<T extends AbstractBaseActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findOptionalView(source, 2131493054, null);
    target.toolbar = finder.castView(view, 2131493054, "field 'toolbar'");
    view = finder.findOptionalView(source, 2131493055, null);
    target.tvTitle = finder.castView(view, 2131493055, "field 'tvTitle'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends AbstractBaseActivity> implements Unbinder {
    private T target;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      target.toolbar = null;
      target.tvTitle = null;
    }
  }
}
