package com.basv.materialnumpad;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MaterialNumberpad extends LinearLayout {

    public static final int BUTTON_0             = 0;
    public static final int BUTTON_1             = 1;
    public static final int BUTTON_2             = 2;
    public static final int BUTTON_3             = 3;
    public static final int BUTTON_4             = 4;
    public static final int BUTTON_5             = 5;
    public static final int BUTTON_6             = 6;
    public static final int BUTTON_7             = 7;
    public static final int BUTTON_8             = 8;
    public static final int BUTTON_9             = 9;
    public static final int BUTTON_ACTION_DELETE = 10;
    public static final int BUTTON_ACTION_DONE   = 11;

    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;

    private int buttonTextColor = DEFAULT_TEXT_COLOR;

    private boolean showDoneAction;

    private TextView  button0;
    private TextView  button1;
    private TextView  button2;
    private TextView  button3;
    private TextView  button4;
    private TextView  button5;
    private TextView  button6;
    private TextView  button7;
    private TextView  button8;
    private TextView  button9;
    private ImageView imageActionDelete;
    private ImageView imageActionDone;
    private View      buttonActionDone;

    private Drawable drawableActionDone;


    private OnNumpadClickListener clickListener;
    private EditText              edittext;

    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({BUTTON_0, BUTTON_1, BUTTON_2, BUTTON_3, BUTTON_4, BUTTON_5, BUTTON_6, BUTTON_7, BUTTON_8, BUTTON_9, BUTTON_ACTION_DELETE, BUTTON_ACTION_DONE})
    public @interface NumpadAction {
    }

    public interface OnNumpadClickListener {
        void onNumpadClick(@NumpadAction int action, String value);

        void onDoneClick(View view);

        void onDeleteClick(View view);
    }

    public void setOnNumpadClickListener(OnNumpadClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public MaterialNumberpad(Context context) {
        super(context);

        inflateView();
    }

    public MaterialNumberpad(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialNumberpad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MaterialNumpad, defStyleAttr, 0);
        buttonTextColor = a.getColor(R.styleable.MaterialNumpad_mnp_text_color, DEFAULT_TEXT_COLOR);
        drawableActionDone = a.getDrawable(R.styleable.MaterialNumpad_mnp_src_done_action);
        a.recycle();

        inflateView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaterialNumberpad(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        inflateView();
    }

    private void inflateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.material_numpad_view, this, false);

        button0 = (TextView) view.findViewById(R.id.button_0);
        button1 = (TextView) view.findViewById(R.id.button_1);
        button2 = (TextView) view.findViewById(R.id.button_2);
        button3 = (TextView) view.findViewById(R.id.button_3);
        button4 = (TextView) view.findViewById(R.id.button_4);
        button5 = (TextView) view.findViewById(R.id.button_5);
        button6 = (TextView) view.findViewById(R.id.button_6);
        button7 = (TextView) view.findViewById(R.id.button_7);
        button8 = (TextView) view.findViewById(R.id.button_8);
        button9 = (TextView) view.findViewById(R.id.button_9);
        imageActionDone = (ImageView) view.findViewById(R.id.image_action_done);
        imageActionDelete = (ImageView) view.findViewById(R.id.image_action_delete);

        View buttonActionDelete = view.findViewById(R.id.button_action_delete);

        button0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_0);
            }
        });
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_1);
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_2);
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_3);
            }
        });
        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_4);
            }
        });
        button5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_5);
            }
        });
        button6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_6);
            }
        });
        button7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_7);
            }
        });
        button8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_8);
            }
        });
        button9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_9);
            }
        });
        buttonActionDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(BUTTON_ACTION_DELETE);
            }
        });

        buttonActionDone = view.findViewById(R.id.button_action_done);


        if (drawableActionDone != null) {
            this.showDoneAction = true;
            this.imageActionDone.setImageDrawable(drawableActionDone);
        }


        addView(view);

        updateView();
    }

    public void setEdittext(EditText edittext) {
        this.edittext = edittext;
    }

    private void updateView() {
        setupButtonColors();

        if (showDoneAction) {
            buttonActionDone.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onDoneClick(buttonActionDone);
                    handleButtonClick(BUTTON_ACTION_DONE);
                }
            });
        } else {
            buttonActionDone.setVisibility(View.INVISIBLE);
        }
    }

    private void setupButtonColors() {
        button0.setTextColor(buttonTextColor);
        button1.setTextColor(buttonTextColor);
        button2.setTextColor(buttonTextColor);
        button3.setTextColor(buttonTextColor);
        button4.setTextColor(buttonTextColor);
        button5.setTextColor(buttonTextColor);
        button6.setTextColor(buttonTextColor);
        button7.setTextColor(buttonTextColor);
        button8.setTextColor(buttonTextColor);
        button9.setTextColor(buttonTextColor);
        imageActionDone.setColorFilter(buttonTextColor);
        imageActionDelete.setColorFilter(buttonTextColor);
    }

    private void handleButtonClick(@NumpadAction int action) {

        if (clickListener == null) {
            return;
        }

        switch (action) {
            case MaterialNumberpad.BUTTON_0:
            case MaterialNumberpad.BUTTON_1:
            case MaterialNumberpad.BUTTON_2:
            case MaterialNumberpad.BUTTON_3:
            case MaterialNumberpad.BUTTON_4:
            case MaterialNumberpad.BUTTON_5:
            case MaterialNumberpad.BUTTON_6:
            case MaterialNumberpad.BUTTON_7:
            case MaterialNumberpad.BUTTON_8:
            case MaterialNumberpad.BUTTON_9:
                String val = String.valueOf(action);
                clickListener.onNumpadClick(action, val);
                if (edittext != null) edittext.append(val);
                return;
        }

        if ((edittext != null && edittext.length() != 0) && action == BUTTON_ACTION_DELETE) {
            edittext.getText()
                    .delete(edittext.length() - 1, edittext.length());
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);
        ss.buttonTextColor = buttonTextColor;
        ss.showDoneAction = showDoneAction;

        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        this.buttonTextColor = ss.buttonTextColor;
        this.showDoneAction = ss.showDoneAction;

        updateView();
    }

    private static class SavedState extends BaseSavedState {

        private int     buttonTextColor;
        private boolean showDoneAction;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel source) {
            super(source);
            buttonTextColor = source.readInt();
            showDoneAction = source.readByte() == 0x01;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(buttonTextColor);
            out.writeByte((byte) (showDoneAction ? 0x01 : 0x00));
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
