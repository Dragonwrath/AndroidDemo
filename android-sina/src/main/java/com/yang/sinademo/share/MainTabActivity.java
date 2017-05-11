package com.yang.sinademo.share;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.yang.sinademo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainTabActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.id)
    TextView mId;
    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.activity_main_tab)
    LinearLayout mActivityMainTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            mId.setText(bundle.getString("id"));
//            mName.setText(bundle.getString("name"));
//            Glide.with(this).load(bundle.getString("url")).into(mImage);
//        } else {
//            setContentView(R.layout.activity_invilidate_login);
//        }


    }

    @SuppressLint("NewApi")
    public void share(View view) {
        PopupWindow popupWindow = new PopupWindow(this);
        ViewGroup group =(ViewGroup) getLayoutInflater().inflate(R.layout.share,mActivityMainTab,false);
        int childCount = group.getChildCount();
        for (int i = 0; i < childCount; i++) {
            group.getChildAt(i).setOnClickListener(this);
        }
        popupWindow.setContentView(group);
        ColorDrawable background = new ColorDrawable();
        background.setColor(Color.WHITE);
        popupWindow.setBackgroundDrawable(background);
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(mActivityMainTab, Gravity.BOTTOM ,0,0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_wechat:
                break;
            case R.id.share_weibo:
                shareHandler = new WbShareHandler(this);
                shareHandler.registerApp();
                WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
                weiboMessage.textObject = getTextObj();
                weiboMessage.imageObject = getImageObj();
//                weiboMessage.mediaObject = getWebpageObj();
                shareHandler.shareMessage(weiboMessage, false);
                break;
        }
    }

    public static final int SHARE_ALL_IN_ONE = 2;


    private WbShareHandler shareHandler;

    private int mShareType = SHARE_ALL_IN_ONE;

    /**
     * 创建文本消息对象。
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        textObject.title = "分享文本";
        textObject.actionUrl = "http://s4.uczzd.cn/ucnews/news?app=ucnews-iflow&aid=17106278003054546318&cid=100&zzd_from=ucnews-iflow&uc_param_str=dndseiwifrvesvntgipf&rd_type=share&pagetype=share&btifl=100&sdkdeep=2&sdksid=55a8f9d3-223a-d5a7-812c-c3af12bb6430&sdkoriginal=55a8f9d3-223a-d5a7-812c-c3af12bb6430";
        return textObject;
    }

    /**
     * 获取分享的文本模板。
     */
    private String getSharedText() {
        return "70岁富翁拥有两座城堡 抛弃伴侣想找年轻女子生育继承人#UC头条#";
    }


    /**
     * 创建图片消息对象。
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_logo);
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    //分享多媒体
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title ="测试title";
        mediaObject.description = "测试描述";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_logo);
//        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
//        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml";
        mediaObject.defaultText = "Webpage 默认文案";
        return mediaObject;
    }
}
