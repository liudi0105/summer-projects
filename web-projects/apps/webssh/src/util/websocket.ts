export interface Handler {
  onMessage: (data: string) => void;
  onClose?: () => void;
  onConnect?: () => void;
  onError?: (e: Event) => void;
}

export class WsConnection {
  private conn: WebSocket;

  constructor(private endpoint: string, handler: Handler) {
    this.conn = new WebSocket(endpoint);
    this.connect(handler);
  }

  send = (data: object) => {
    this.conn.send(JSON.stringify(data));
  };

  sendInitData = (options: object) => {
    //连接参数
    this.conn.send(JSON.stringify(options));
  };

  sendClientData = (data: string) => {
    //发送指令
    this.conn.send(JSON.stringify({ operate: "command", command: data }));
  };

  connect = (handler: Handler) => {
    const {
      onConnect = () => {
        console.log("websocket connected");
      },
      onClose = () => {
        console.log("websocket closed");
      },
      onError = (e) => {
        console.error(e);
      },
    } = handler;

    this.conn.onopen = onConnect;
    this.conn.onclose = onClose;
    this.conn.onerror = onError;
    this.conn.onmessage = (e) => handler.onMessage(e.data.toString());
  };
}
