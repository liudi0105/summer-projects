import { Terminal } from "@xterm/xterm";
import { WsConnection } from "./websocket";
import { FitAddon } from "@xterm/addon-fit";
import { WebglAddon } from "@xterm/addon-webgl";
import { WebLinksAddon } from "@xterm/addon-web-links";
import { ClipboardAddon } from "@xterm/addon-clipboard";

interface OpenTerminalOptions {
  operate: "connect";
  host: string;
  port: number;
  username: string;
  password: string;
}

export function openTerminal(
  options: OpenTerminalOptions,
  parent: HTMLElement
) {
  const fitAddon = new FitAddon();
  const term = new Terminal({
    // cols: 97,
    // rows: 37,
    cursorBlink: true, // 光标闪烁
    cursorStyle: "block", // 光标样式  null | 'block' | 'underline' | 'bar'
    // scrollback: 800, //回滚
    tabStopWidth: 8, //制表宽度
  });

  term.onData((data: string) => {
    //键盘输入时的回调函数
    ws.sendClientData(data);
  });
  term.loadAddon(fitAddon);
  term.loadAddon(new WebLinksAddon());
  term.loadAddon(new WebglAddon());
  term.loadAddon(new ClipboardAddon());
  term.open(parent);
  fitAddon.fit();
  //在页面上显示连接中...
  term.write("Connecting...");
  const endpoint = "ws://127.0.0.1:8080/webssh";
  const ws = new WsConnection(endpoint, {
    onError: function (error: Event) {
      //连接失败回调
      term.write("Error: " + error + "\r\n");
    },
    onConnect: function () {
      //连接成功回调
      ws.sendInitData(options);
    },
    onClose: function () {
      //连接关闭回调
      term.write("\rconnection closed");
    },
    onMessage: function (data: string) {
      //收到数据时回调
      term.write(data);
    },
  });
}
