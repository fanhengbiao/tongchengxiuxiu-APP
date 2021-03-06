package com.example.administrator.learn.Tool;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.learn.ServceTool.ApiService;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Administrator on 2016/12/28.
 */

public class ShareUtils {
    public interface setShareListener {
        /**
         * @param  isSuccessful 是否成功
         * @param iscallback 是否需要告诉后台
         */
        void shareSuccess(boolean isSuccessful ,boolean iscallback);
    }
//
//    /**
//     * 微信分享
//     *
//     * @param platform  类型
//     * @param title
//     * @param
//     * @param text      imageUrl  图片url
//     * @param wechatUrl url仅在微信（包括好友和朋友圈）中使用wechatUrl
//     *                  commentText   comment是我对这条分享的评论，仅在人人网和QQ空间使用
//     */
//    public static void showShare(Context context, String platform, String title, String text, String imageUrl, String wechatUrl) {
//        final OnekeyShare oks = new OnekeyShare();
//        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
//        if (platform != null) {
//            oks.setPlatform(platform);
//        }
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(title);
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText(text);
//        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl(imageUrl);
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用wechatUrl
//        oks.setUrl(wechatUrl);
//        //启动分享
//        oks.show(context);
//
//    }

    /**
     * 微信分享最好绕过审核分享，这样造成分享朋友圈的时分享的url不显示，而且需要设置分享类型
     * 微信朋友分享
     *
     * @param title
     * @param imageUrl setShareListener 接口回调出去
     *                 iscallback 是否要告诉后台使用
     */
    public static void shareweixin(final Context context, String content, String title, String imageUrl, String wechatUrl, final boolean iscallback, final setShareListener setShareListener) {
        Wechat.ShareParams wechat = new Wechat.ShareParams();
        wechat.setShareType(Platform.SHARE_WEBPAGE);
        wechat.setText(content);
        wechat.setTitle(title);
        wechat.setImageUrl(imageUrl);
        wechat.setUrl(wechatUrl);
        Platform weixin = ShareSDK.getPlatform(Wechat.NAME);
        weixin.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                UtilTool.ShowToast(context, "分享成功");
                if (setShareListener != null) {
                    setShareListener.shareSuccess(true, iscallback);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                UtilTool.ShowToast(context, "分享失败");
                if (setShareListener != null) {
                    setShareListener.shareSuccess(false, iscallback);
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                UtilTool.ShowToast(context, "分享取消");
                if (setShareListener != null) {
                    setShareListener.shareSuccess(false, iscallback);
                }
            }
        }); // 设置分享事件回调
        if (!weixin.isClientValid()) {
            UtilTool.ShowToast(context, "没有安装了微信");
        }
        weixin.share(wechat);
    }

    /**
     * 微信分享最好绕过审核分享，这样造成分享朋友圈的时分享的url不显示,而且需要设置分享类型
     * 微信朋友圈分享
     *
     * @param context
     * @param title
     * @param
     * @param imageUrl
     * @param wechatUrl
     * @param setShareListener
     */
    public static void shareWechatMoments(final Context context,String content, String title, String imageUrl, String wechatUrl, final boolean iscallback, final setShareListener setShareListener) {
        WechatMoments.ShareParams wechatMoments = new WechatMoments.ShareParams();
        wechatMoments.setShareType(Platform.SHARE_WEBPAGE);
        wechatMoments.setText(content);
        wechatMoments.setTitle(title);
        wechatMoments.setImageUrl(imageUrl);
        wechatMoments.setUrl(wechatUrl);
        Platform weixinfriead = ShareSDK.getPlatform(WechatMoments.NAME);
        weixinfriead.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                UtilTool.ShowToast(context, "分享成功");
                if (setShareListener != null) {
                    setShareListener.shareSuccess(true, iscallback);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                UtilTool.ShowToast(context, "分享失败");
                if (setShareListener != null) {
                    setShareListener.shareSuccess( false,iscallback);
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                UtilTool.ShowToast(context, "分享取消");
                if (setShareListener != null) {
                    setShareListener.shareSuccess(false, iscallback);
                }
            }
        }); // 设置分享事件回调
        if (!weixinfriead.isClientValid()) {
            UtilTool.ShowToast(context, "没有安装了微信");
        }
        weixinfriead.share(wechatMoments);
    }

    /**
     * 新浪微博分享
     *
     * @param title 标题
     *  content  内容
     * @param imageUrl setShareListener 接口回调出去
     */
    public static void shareSinaWei(final Context context, String content,String title, String imageUrl, final boolean iscallback, final setShareListener setShareListener) {
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setText(content);
        sp.setImageUrl(imageUrl);
        sp.setTitle(title);
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess(true, iscallback);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess( false,iscallback);
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(context, "分享取消", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess( false,iscallback);
                }
            }
        }); // 设置分享事件回调
// 执行图文分享
        weibo.share(sp);
    }

    /**
     * qq分享
     *
     * @param content 内容
     * @param url
     * title 标题
     * @param imageUrl setShareListener 接口回调出去
     */
    public static void shareQQ(final Context context, String content,String title, String url, String imageUrl, final boolean iscallback, final setShareListener setShareListener) {
        QQ.ShareParams sp = new QQ.ShareParams();
        sp.setText(content);
        sp.setTitle(title);
        sp.setImageUrl(imageUrl);
        sp.setTitleUrl(url);
        Platform weibo = ShareSDK.getPlatform(QQ.NAME);
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess(true, iscallback);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess(false, iscallback);
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(context, "分享取消", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess(false, iscallback);
                }
            }
        }); // 设置分享事件回调
// 执行图文分享
        weibo.share(sp);
    }

    /**
     * qq空间分享
     *
     * @param content 内容
     * @param url
     * title标题
     * @param imageUrl setShareListener 接口回调出去
     */
    public static void shareQZone(final Context context, String content,String title, String url, String imageUrl, String appName, final boolean iscallback, final setShareListener setShareListener) {
        QZone.ShareParams sp = new QZone.ShareParams();
        sp.setText(content);
        sp.setTitle(title);
        sp.setImageUrl(imageUrl);
        sp.setTitleUrl(url);
        sp.setSite(appName);
        Platform weibo = ShareSDK.getPlatform(QZone.NAME);
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess(true, iscallback);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess(false, iscallback);
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(context, "分享取消", Toast.LENGTH_SHORT).show();
                if (setShareListener != null) {
                    setShareListener.shareSuccess(false, iscallback);
                }
            }
        }); // 设置分享事件回调
// 执行图文分享
        weibo.share(sp);
    }
    /**
     * 显示popuview对话框
     * @param context
     * @param title
     * @param imageUrl
     * @param shareUrl
     * @param view
     * @param setShareListener
     * isbackground  点击分享当前页面会在后台，告诉后端
     */
    public static  void showPopuViewDialog(final  Context context,final String content, final  String title, final  String imageUrl, final String shareUrl, final View view, final boolean isbackground, final  setShareListener setShareListener) {

        SelecPopuview picPopupWindow = new SelecPopuview(context, new SelecPopuview.setonListener() {
            @Override
            public void setonlistfener(View view, int index) {
                if (TextUtils.isEmpty(shareUrl)) {
                    UtilTool.ShowToast(context, "分享失败");
                    return;
                }
                if (isbackground){
                    //告诉后端我当前的activity在后台运行中
                    ApiService.SetLive(context,SPUtils.getliveid(context));
                }
                switch (index) {
                    case SelecPopuview.WEIBO:
                        ShareUtils.shareSinaWei(context,content, title, imageUrl, true, setShareListener);
                        break;
                    case SelecPopuview.WEIXIN:
                        ShareUtils.shareweixin(context, content, title, imageUrl, shareUrl, true, setShareListener);
                        break;
                    case SelecPopuview.FRIEND:
                        ShareUtils.shareWechatMoments(context, content, title, imageUrl, shareUrl, true, setShareListener);
                        break;
                    case SelecPopuview.QQ:
                        ShareUtils.shareQQ(context,content, title, shareUrl, imageUrl, true, setShareListener);
                        break;
                    case SelecPopuview.QQZONE:
                        ShareUtils.shareQZone(context,content, title, shareUrl, imageUrl, "同城秀秀", true, setShareListener);
                        break;
                }
            }
        });
        //设置layout在PopupWindow中显示的位置
        picPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }



}
