package com.joker.tool.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.joker.tool.R;
import android.tools.Unfinished;

/**
 * The wrapper class of system dialog {@link AlertDialog}.
 *
 * We use this to custom our dialog to hold positive event or negative event
 * There are something that we should know about this class:
 * 1、Maybe we use a non-default layout id for our customization,wo should
 * specify the special id to special view,you should follow below advise:
 *    -title    to Title Text
 *    -content  to Message Text
 *    -positive to Positive Button
 *    -negative to Negative Button
 */
public class StandardDialog extends AlertDialog {

    public StandardDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static Dialog defaultBuilder(Activity act,String title,String content,
                                 boolean cancelable) {
        final Builder builder = new Builder(act,R.style.BaseDialog, R.layout.standard_dialog);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case BUTTON_POSITIVE:
                    case BUTTON_NEGATIVE:
                    default:
                        dialog.dismiss();
                }
            }
        };
        builder.setTitle(title)
                .setContent(content)
                .setCancelable(cancelable)
                .setPositiveListener(listener);
        return builder.create();
    }


    @Unfinished(description = {"缺少特定功能"})
    public static class Builder {
        private AlertDialog dialog;
        private View rootView;
        public Builder(Activity act) {
            this(act, 0, R.layout.standard_dialog);
        }

        public Builder(Activity act, @StyleRes int theme, @LayoutRes int layoutRes) {
            AlertDialog.Builder builder = new AlertDialog.Builder(act, theme);

            rootView = act.getLayoutInflater().inflate(layoutRes, (ViewGroup) act.getWindow().getDecorView(), false);
            builder.setView(rootView);
            dialog = builder.create();

            //Fellow step should be deprecated. We use inflate this view first, so that we could hold
            //this view of this layout

            //if we don't use show() method that a NullPointerException will be thrown when
            // we use dialog.findViewById().And we also could not use Activity.findViewById()
            //because dialog and activity each have different window .
//            dialog.show();
//            dialog.hide();
        }

        public Builder setCancelable(boolean cancelable) {
            dialog.setCancelable(cancelable);
            return this;
        }

        public Builder setTitle(@StringRes int stringId) {
            setTitle(dialog.getContext().getResources().getString(stringId));
          return this;
        }

        public Builder setContent(@StringRes int stringId) {
            setContent(dialog.getContext().getResources().getString(stringId));
            return this;
        }

        public Builder setTitle(String string) {
            TextView view = (TextView) rootView.findViewById(R.id.title);
            view.setText(string);
            return this;
        }

        public Builder setContent(String string) {
            TextView view = (TextView)rootView.findViewById(R.id.content);
            view.setText(string);
            return this;
        }

        public Builder setPositiveText(@StringRes int stringId) {
            Button view = (Button) rootView.findViewById(R.id.btn_confirm);
            view.setText(stringId);
            return this;
        }

        public Builder setPositiveListener(final DialogInterface.OnClickListener listener) {
            Button view = (Button) rootView.findViewById(R.id.btn_confirm);
            view.setVisibility(View.VISIBLE);
            final CharSequence text = view.getText();
            //If we set the listener, we must have the text of this button
            if (!TextUtils.isEmpty(text)) {
                view.setText(dialog.getContext().getResources().getString(R.string.confirm));
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
            });
            final Bundle state = dialog.onSaveInstanceState();
            return this;
        }

        public Builder setNegativeText(@StringRes int stringId) {
            Button view = (Button) rootView.findViewById(R.id.btn_cancel);
            view.setText(stringId);
            return this;
        }

        public Builder setNegativeListener(final DialogInterface.OnClickListener listener) {
            final Button view = (Button) rootView.findViewById(R.id.btn_cancel);
            view.setVisibility(View.VISIBLE);
            final CharSequence text = view.getText();
            if (!TextUtils.isEmpty(text)) {
                view.setText(dialog.getContext().getResources().getString(R.string.cancel));
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                }
            });
            return this;
        }

        public Dialog create() {
            return dialog;
        }
    }
}
