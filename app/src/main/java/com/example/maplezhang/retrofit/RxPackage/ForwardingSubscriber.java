package com.example.maplezhang.retrofit.RxPackage;

import rx.Subscriber;

public class ForwardingSubscriber<T> extends Subscriber<T> {
  private final Subscriber<T> delegate;

  public ForwardingSubscriber(Subscriber<T> delegate) {
    this.delegate = delegate;
  }

  @Override public void onNext(T value) {
    delegate.onNext(value);
  }

  @Override public void onCompleted() {
    delegate.onCompleted();
  }

  @Override public void onError(Throwable throwable) {
    delegate.onError(throwable);
  }
}
