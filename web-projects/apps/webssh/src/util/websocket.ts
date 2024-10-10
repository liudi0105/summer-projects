
export class WSSHClient {
  private _connection?: WebSocket;

  _generateEndpoint = () => {
    return "ws://127.0.0.1:8080/webssh";
  };

  connect(options: any) {
    const endpoint = this._generateEndpoint();

    if (window.WebSocket) {
      //如果支持websocket
      this._connection = new WebSocket(endpoint);
    } else {
      //否则报错
      options.onError("WebSocket Not Supported");
      return;
    }

    this._connection.onopen = function () {
      options.onConnect();
    };

    this._connection.onmessage = function (evt) {
      const data = evt.data.toString();
      //data = base64.decode(data);
      options.onData(data);
    };

    this._connection.onclose = () => {
      options.onClose();
    };
  }

  send(data: object) {
    this._connection?.send(JSON.stringify(data));
  }

  sendInitData(options: object) {
    //连接参数
    this._connection?.send(JSON.stringify(options));
  }

  sendClientData(data: string) {
    //发送指令
    this._connection?.send(
      JSON.stringify({ operate: "command", command: data })
    );
  }
}
