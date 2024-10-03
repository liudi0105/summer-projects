import { Table } from "@common-module/common-antd";
import { AppPageResult } from "@common-module/common-types";
import { useEffect, useState } from "react";
import { container } from "../container";
import { UserAccountEntity, UserAccountService } from "../servcies/UserService";

const userService = container.get(UserAccountService)

export const UserView = () => {

  const [data, setData] = useState<AppPageResult<UserAccountEntity>>()

  useEffect(() => {
    userService.listPaged({
      pageIndex: 1,
      pageSize: 10,
    }).then(setData);
  }, []);

  return (
    <div>
      <Table dataSource={data?.content ?? []}></Table>
    </div>
  );
};
