package com.okcoin.commons.okex.open.api.test.ws.option;


import com.okcoin.commons.okex.open.api.test.ws.option.config.WebSocketClient;
import com.okcoin.commons.okex.open.api.test.ws.option.config.WebSocketConfig;
import org.apache.commons.compress.utils.Lists;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;

/**
 * 公共频道
 * public channel
 *
 * @author oker
 * @date 2019/7/5 1:40 AM
 */
public class OptionPublicChannelTest {

    private static final WebSocketClient webSocketClient = new WebSocketClient();
    private static Logger logger = Logger.getLogger(OptionPublicChannelTest.class);

    @Before
    public void connect() {
        //与服务器建立连接
        WebSocketConfig.publicConnect(webSocketClient);
    }

    @After
    public void close() {
        System.out.println(Instant.now().toString() + " close connect!");
        webSocketClient.closeConnection();
    }


    /**
     * 合约信息
     * Ticker Channel
     */
    @Test
    public void instrumentsChannel() {
        //添加订阅频道
        ArrayList<String> channel = Lists.newArrayList();
        channel.add("option/instruments:TBTC-USD");
        //调用订阅方法
        webSocketClient.subscribe(channel);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 公共-ticker频道
     * Ticker Channel
     */
    @Test
    public void tickerChannel() {
        //添加订阅频道
        ArrayList<String> channel = Lists.newArrayList();
        channel.add("option/ticker:TBTC-USD-191227-12500-C");
        //调用订阅方法
        webSocketClient.subscribe(channel);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-k线频道
     * 频道列表：
     * futures/candle60s // 1分钟k线数据频道
     * futures/candle180s // 3分钟k线数据频道
     * futures/candle300s // 5分钟k线数据频道
     * futures/candle900s // 15分钟k线数据频道
     * futures/candle1800s // 30分钟k线数据频道
     * futures/candle3600s // 1小时k线数据频道
     * futures/candle7200s // 2小时k线数据频道
     * futures/candle14400s // 4小时k线数据频道
     * futures/candle21600 // 6小时k线数据频道
     * futures/candle43200s // 12小时k线数据频道
     * futures/candle86400s // 1day k线数据频道
     * futures/candle604800s // 1week k线数据频道
     */
    @Test
    public void klineChannel() {
        //logger.info("info信息");
        //logger.debug("debug信息");
        //添加订阅频道
        ArrayList<String> channel = Lists.newArrayList();
        channel.add("option/candle60s:TBTC-USD-191227-12500-C");
        //调用订阅方法
        webSocketClient.subscribe(channel);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-交易频道
     * Trade Channel
     */
    @Test
    public void tradeChannel() {
        //添加订阅频道
        ArrayList<String> channel = Lists.newArrayList();
        channel.add("option/trade:TBTC-USD-191227-12500-C");
        //调用订阅方法
        webSocketClient.subscribe(channel);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-合约详细定价频道
     * estimatedPrice Channel
     */
    @Test
    public void estimatedPriceChannel() {
        //添加订阅频道
        ArrayList<String> channel = Lists.newArrayList();
        channel.add("option/summary:TBTC-USD");
        //调用订阅方法
        webSocketClient.subscribe(channel);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 公共-5档深度
     * Depth5 Channel
     */
    @Test
    public void depth5Channel() {
        //添加订阅频道
        ArrayList<String> channel = Lists.newArrayList();
        channel.add("option/depth5:TBTC-USD-191227-12500-C");
        //调用订阅方法
        webSocketClient.subscribe(channel);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共-200档深度
     * Depth Channel
     * 首次返回400档，后续为增量
     */
    @Test
    public void depthChannel() {
        //添加订阅频道
        ArrayList<String> channel = Lists.newArrayList();
        //channel.add("swap/depth:LTC-USD-SWAP");
        //200档位深度校验
        channel.add("option/depth:TBTC-USD-191227-12500-C");
        //调用订阅方法
        webSocketClient.subscribe(channel);
        //为保证测试方法不停，需要让线程延迟
        try {
            Thread.sleep(100000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //取消订阅
    @Test
    public void unsubscribeChannel() {
        ArrayList<String> list = Lists.newArrayList();
        //添加要取消订阅的频道名
        list.add("futures/depth5:BTC-USD-191227");
        //订阅
        webSocketClient.subscribe(list);
        //取消订阅
        webSocketClient.unsubscribe(list);
        //为保证收到服务端返回的消息，需要让线程延迟
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
