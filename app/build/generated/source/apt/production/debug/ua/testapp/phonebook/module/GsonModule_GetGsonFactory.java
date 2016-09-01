package ua.testapp.phonebook.module;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GsonModule_GetGsonFactory implements Factory<Gson> {
  private final GsonModule module;

  public GsonModule_GetGsonFactory(GsonModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Gson get() {
    return Preconditions.checkNotNull(
        module.getGson(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Gson> create(GsonModule module) {
    return new GsonModule_GetGsonFactory(module);
  }
}
