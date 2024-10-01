import { Table } from "@common-module/common-antd";
import { container } from "../container";
import { UserAccountService } from "../servcies/UserService";
import { useEffect } from "react";

const userService = container.get(UserAccountService)

export const UserView = () => {

  useEffect(() => {
    userService.listPaged({
      pageIndex: 1,
      pageSize: 10
    })
  }, [])

  return <div>
    <Table data></Table>
  </div>;
};
