package org.geektimes.projects.user.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

public class MonoDemo {

    public static void main(String[] args) {
        Mono.just("test") // 数据绑定
                .map(v -> "") // "test" -> ""
                .subscribe(new Subscriber<String>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                    }

                    @Override
                    public void onNext(String s) {
                        if (s.isEmpty()) {
                            subscription.cancel();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
