package gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attack.connectivity.server.constraints;

import android.content.Context;

public abstract class NetworkConstraint {
    public abstract void resolve(Context context);

    public abstract boolean isResolved(Context context);

    public static class UnresolvedNetworkConstraintException extends Exception {
        public UnresolvedNetworkConstraintException(String message) {
            super(message);
        }
    }
}