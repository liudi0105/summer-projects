import { Terminal } from "@xterm/xterm";
import { WSSHClient } from "./websocket";

interface OpenTerminalOptions {
  operate: "connect";
  host: string;
  port: number;
  username: string;
  password: string;
}

export function openTerminal(options: OpenTerminalOptions, parent: HTMLElement) {
  const client = new WSSHClient();
  const term = new Terminal({
    cols: 97,
    rows: 37,
    cursorBlink: true, // 光标闪烁
    cursorStyle: "block", // 光标样式  null | 'block' | 'underline' | 'bar'
    scrollback: 800, //回滚
    tabStopWidth: 8, //制表宽度
  });

  term.onData((data: string) => {
    //键盘输入时的回调函数
    client.sendClientData(data);
  });
  term.open(parent);
  //在页面上显示连接中...
  term.write("Connecting...");
  //执行连接操作
  client.connect({
    onError: function (error: Error) {
      //连接失败回调
      term.write("Error: " + error + "\r\n");
    },
    onConnect: function () {
      //连接成功回调
      client.sendInitData(options);
    },
    onClose: function () {
      //连接关闭回调
      term.write("\rconnection closed");
    },
    onData: function (data: string) {
      //收到数据时回调
      term.write(data);
    },
  });
}
