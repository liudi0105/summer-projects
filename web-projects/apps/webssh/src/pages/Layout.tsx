export const Layout = () => {
  return <div></div>;
};

export interface Handler {

  onData: (data: string) => void

  onClose: () => void

}

export class Session {
  constructor(
    private prop: {
      host: string;
      port: number;
      username: string;
      password: string;
    }
  ) {}

  connect = () => {
    const endpoint = "ws://127.0.0.1:8080/webssh";
    const connection = new WebSocket(endpoint);
    connection.onopen = () => {
    }
  };
}
