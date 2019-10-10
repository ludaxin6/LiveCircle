package com.deshine.huishu.app.api;

/**
 * 公共错误码定义
 */
public interface ResultCode {

    String SUCCESS = "000000";
    String SUCCESS_DESC = "成功";


    interface Base {
        interface System {
            String SYSTEM_ERROR = "100000";
            String SYSTEM_ERROR_DESC = "系统错误";
        }

        interface Param {//参数
            String EMPTY = "101010";
            String EMPTY_DESC = "参数丢失";
            String ERROR = "101011";
            String ERROR_DESC = "参数错误";
        }

        interface Data {//数据
            String LOST = "102010";
            String LOST_DESC = "数据丢失";
            String EXIST = "102011";
            String EXIST_DESC = "数据已存在";
        }

        interface Db {//数据库
            String ACCESS_ERROR = "103011";
            String ACCESS_ERROR_DESC = "访问异常";
            String INSERT_ERROR = "103012";
            String INSERT_ERROR_DESC = "插入失败";
            String UPDATE_ERROR = "103013";
            String UPDATE_ERROR_DESC = "更新失败";
            String NO_RESULT = "103014";
            String NO_RESULT_DESC = "无返回结果";
            String DELETE_ERROR = "103015";
            String DELETE_ERROR_DESC = "删除失败";
        }


        interface Auth {//权限
            String TOKEN_EXPIRE = "104001";
            String TOKEN_EXPIRE_DESC = "登陆信息已过期";
            String INVALID_TOKEN = "104002";
            String INVALID_TOKEN_DESC = "账号已在其他地方登陆";
            String NO_AUTH = "104003";
            String NO_AUTH_DESC = "没有权限";
            String NO_TOKEN = "104004";
            String NO_TOKEN_DESC = "鉴权信息缺失";
            String NO_BINDING_INFO = "104005";
            String NO_BINDING_INFO_DESC = "客户-MAC地址绑定信息缺失";
        }

        interface Cache {//缓存
            String TRANSLATE_ERROR = "105000";
            String TRANSLATE_ERROR_DESC = "转换错误";
            String DATA_LOST = "105010";
            String DATA_LOST_DESC = "数据丢失";
            String TIME_OUT="105020";
            String TIME_OUT_DESC="超时";
        }

        interface Websocket {//websocket
            String DELETE_MEMBER_ERROR_CASE = "106010";
            String DELETE_MEMBER_ERROR_CASE_DESC = "商机拥有者不能删除";
            String DELETE_MEMBER_ERROR_DIRECT_LEADER = "106011";
            String DELETE_MEMBER_ERROR_DIRECT_LEADER_DESC = "不能删除商机拥有者的直属领导";
        }

        interface Affix {//附件
            String AFFIX_NOT_FOUND = "106510";
            String AFFIX_NOT_FOUND_DESC = "未找到附件";
            String AFFIX_DAMAGED = "106511";
            String AFFIX_DAMAGED_DESC = "附件文件破损";
        }

        interface Mail {//邮件

        }

        interface Flow {//流程

            interface Definition {
                String DATA_LOST = "108005";
                String DATA_LOST_DESC = "流程定义数据丢失";
                String NO_FOLLOW_EXECUTOR = "108003";
                String NO_FOLLOW_EXECUTOR_DESC = "无后续处理人";
                String NO_FOLLOW_NODE = "108004";
                String NO_FOLLOW_NODE_DESC = "无后续节点";
            }

            interface Instance {
                String INSTANCE_DATA_LOST = "108001";
                String INSTANCE_DATA_LOST_DESC = "实例数据丢失";
                String IRREVERSIBLE = "108006";
            }

            interface Process {
                String PROCESS_DATA_LOST = "108002";
                String PROCESS_DATA_LOST_DESC = "节点数据丢失";

                String PROCESS_PRODUCED = "108003";
                String PROCESS_PRODUCED_DESC = "任务已执行";
            }
        }

        interface Printer {//打印服务
            String NO_SERVER = "109110";
            String NO_SERVER_DESC = "没有找到打印服务器地址";
        }

        interface Repeat {//权限
            String REQUEST_REPEAT = "110001";
            String REQUEST_REPEAT_DESC = "请勿重复操作";
        }

    }

    interface Biz {
        interface User {//用户 //11XXXX
            String DATA_LOST = "111001";
            String DATA_LOST_DESC = "用户数据丢失";
            String LOGIN_ERROR = "111002";
            String LOGIN_ERROR_DESC = "用户名或密码错误";
            String NO_EMAIL = "111003";
            String ID_CARD_NO_ERROR = "111004";
            String NO_MOBILE_PHONE = "111005";
        }

        interface Customer {//客户 //12XXXX
            String DATA_LOST = "120010";
            String DATA_LOST_DESC = "客户数据丢失";
            String DELETE_ERROR_WITH_CASE = "120011";
            String DELETE_ERROR_WITH_CASE_DESC = "有商机客户无法删除";
            String DELETE_ERROR_WITH_AR = "120012";
            String DELETE_ERROR_WITH_AR_DESC = "有应收客户无法删除";
            String TRANS_ERROR_WITH_CASE = "120013";
            String TRANS_ERROR_WITH_CASE_DESC = "有商机客户无法转移";
            String COULD_NOT_UPDATE_NAME = "120014";
            String COULD_NOT_UPDATE_NAME_DESC = "有报价,不允许修改名称";
            String BLACK_LIST_CUSTOMER = "120015";
            String BLACK_LIST_CUSTOMER_DESC = "该客户为高风险客户";

            interface Contact {//联系人
                String DATA_LOST = "121011";
                String DATA_LOST_DESC = "联系人数据丢失";
                String ADD_ERROR = "121012";
                String ADD_ERROR_DESC = "联系人添加失败";
                String UPDATE_ERROR = "121013";
                String UPDATE_ERROR_DESC = "联系人添加失败";

                interface Email {
                    String TOO_MANY_REF = "121110";
                    String TOO_MANY_REF_DESC = "收件箱关联太多联系人";
                }

                interface Phone {
                    String TOO_MANY_REF = "121210";
                    String TOO_MANY_REF_DESC = "关联太多联系人";
                    String INNER_PHONENUM = "121211";
                    String INNER_PHONENUM_DESC = "内部电话";
                }
            }
        }

        interface Hr {//人事
            String NO_BS_DATA = "130001";
            String NO_BS_DATA_DESC = "本月工资费用数据不存在，无法进行佣金提取";
        }

        interface Case4Distributor {//商机 //20XXXX
            String DATA_LOST = "200010";
            String DATA_LOST_DESC = "商机数据丢失";
        }

        interface EnquiryOrder {//询价 //21XXXX

        }

        interface QuoteOrder {//报价 //22XXXX
            String HAS_NO_SEND_QO_TIP = "220001";
            String HAS_NO_SEND_QO_TIP_DESC = "将做过的报价发送客户后，再做新的报价";
            String NOT_MODIFY_HAS_CO_SO = "220002";//该商机已关联销售订单，不能复制新版本
            String NOT_MODIFY_HAS_CO_SO_DESC = "该商机已关联销售订单，不能复制新版本";
        }

        interface ContractOrder {//合同 //23XXXX
            String DATA_LOST = "230010";
            String NOT_RECEIVED_CHEQUE = "230011";
            String NOT_RECEIVED_CHEQUE_DESC = "合同所要求的支票没有收到，请联系财务确认或在交付事项中选择收支票";
            String NOT_TRANSFORM_DATA = "230012";
            String NOT_TRANSFORM_DATA_DESC = "该合同描述内容不能对应到汇数商品编码";
            String NOT_CAN_TRANSFORM_EGO_DATA = "230013";//不能转换来源关联为EGO的部件
            String NOT_MODIFY_HAS_CO_SO = "230014";//该商机已关联销售订单，不能复制新版本
            String NOT_MODIFY_HAS_CO_SO_DESC = "该商机已关联销售订单，不能复制新版本";
            String NOT_CONVERT_HAS_CO_SO = "230015";//该商机已关联销售订单，不能产生新版本合同
            String NOT_CONVERT_HAS_CO_SO_DESC = "该商机已关联销售订单，不能产生新版本合同";
        }

        interface Oa {//oa //30XXXX

            interface DailyReport {//日报  //30XXXX

            }

            interface AidanceTask {//协同任务 //30XXXX
                String DELETE_EXECUTOR_ERROR = "301111";
                String DELETE_EXECUTOR_ERROR_DESC = "协同任务负责人不能删除";
            }
        }

        interface Eorder {//电子面单 //40XXXX
            String EXIST = "400000";//该订单号已经打印
            String EXIST_DESC = "该订单号已经打印，是否继续";
        }

        interface ERP {//ERP 50

            interface OS {//出库单 00
                String AR_AMOUNT_ERROR = "500001";
                String AR_AMOUNT_ERROR_DESC = "收款金额和出库单金额不一致";
                String OUT_QUANTITY_OUTBOUND_ERROR = "500002";
                String OUT_QUANTITY_OUTBOUND_ERROR_DESC = "出库数量大于销售数量,请勿重复出库";
            }

            interface DS {//生产单 01
                String PS_PRINT_COMPLETED = "500101";
                String PS_PRINT_COMPLETED_DESC = "排产单已生产完成，请打印其他生产单";

                String EXIST_PRODUCE = "500102";
                String EXIST_PRODUCE_DESC = "你有生产中的生产单，请先处理后再进行打单";

                String NOT_PRINT_PS = "500103";
                String NOT_PRINT_PS_DESC = "没有排产单";

                String ONLY_GOODS = "500104";
                String ONLY_GOODS_DESC = "只能是商品才能进行领料";

                String NOT_LOCATION_TECH = "500105";
                String NOT_LOCATION_TECH_DESC = "没有仓库的技术员权限";
            }

            interface SC {//收款单 02
                String AMOUNT_ERROR = "500201";
                String AMOUNT_ERROR_DESC = "收款金额不等于核销金额";
                String CHEQUE_EMPTY = "500202";
                String BANK_RECORD_BOOK_REPEAT = "500203";
                String BANK_RECORD_BOOK_REPEAT_DESC = "此流水已记账，请勿重复记账";
                String BANK_RECORD_AMOUNT_ZERO = "500204";
                String BANK_RECORD_AMOUNT_ZERO_DESC = "收款金额不能为空或小于等于0";
                String BANK_RECORD_PAYER_EMPTY = "500205";
                String BANK_RECORD_PAYER_EMPTY_DESC = "客户信息不存在，无法记账";
            }

            interface PO {//采购订单 03
                String AMOUNT_NOT_BALANCE = "500301";
                String AMOUNT_NOT_BALANCE_DESC = "单据金额不平衡";
                String ORDERNO_EXIST = "500302";
                String ORDERNO_EXIST_DESC = "订单号已经用过";
                String POLINE_GOODS_EXIST = "500303";
                String POLINE_GOODS_EXIST_DESC = "商品编码已存在";
                String ORDER_NO_LEN_INVALID = "500304";
            }

            interface PI {//采购入库04
                String NOT_SAME_QUANTITY_SN = "500401";
                String NOT_SAME_QUANTITY_SN_DESC = "本次到货数量与序列号不符";
            }

            interface SNM {//序列号管理05
                String NOT_VALID_ORDER_NO = "500501";
                String NOT_VALID_ORDER_NO_DESC = "不是订单号";
                String NOT_REG_HN = "500502";
                String NOT_REG_HN_DESC = "主机号未登记";
                String HN_PRINTED = "500503";
                String HN_PRINTED_DESC = "该主机号已打单";
                String HN_ORDERNO_ERROR = "500504";
                String HN_ORDERNO_ERROR_DESC = "主机号和订单号不对应";
                String NOT_PRINT_CJ = "500505";
                String NOT_PRINT_CJ_DESC = "未打印拆机单";
                String NOT_NEED_SCAN = "500506";
                String NOT_NEED_SCAN_DESC = "该订单号下商品无需扫码";
                String CAN_NOT_SCAN = "500507";
                String CJ_NOT_SN = "500508";
            }

            interface BOOKED {
                String ACCOUNT_CODE_EMPTY = "500601";
                String ACCOUNT_CODE_EMPTY_DESC = "没有相关记账科目";
                String DETAIL_BIZ_CODE_EMPTY = "500602";
                String DETAIL_BIZ_CODE_EMPTY_DESC = "明细业务代码不能为空";
                String AMOUNT_OVERFULFIL = "500603";
                String AMOUNT_OVERFULFIL_DESC = "核销金额超过余额";
            }

            interface ASSETACCOUNT {
                String CANTDISABLE = "500701";
                String CANTDISABLE_DESC = "账户有余额不能停用";
            }

            interface STORAGELOCATION {
                String CANTDISABLE = "500801";
                String CANTDISABLE_DESC = "有库存不能停用";
            }

            interface ACCOUNT {
                String CANTDISABLE = "500901";
                String CANTDISABLE_DESC = "子科目未全部停用，不可停用";
            }

            //排产单
            interface PS {
                String NOT_LINE = "501001";
                String NOT_LINE_DESC = "排产单无明细";
                //库存不足
                String NOT_ENOUGH_STOCK = "501002";
            }

            interface PY {//付款单
                String NOT_SUFFICIENT_FUNDS = "501101";
                String NOT_SUFFICIENT_FUNDS_DESC = "账户余额不足";
            }

            interface TB {//转账单
                String NO_ENOUGH_MONEY = "501201";
                String NO_ENOUGH_MONEY_DESC = "账户余额不足";
            }

            interface SO//销售订单
            {
                String DIFF_ERROR = "501301";
                String DIFF_ERROR_DESC = "差额超出限定范围";
            }

            interface SA//销售变更
            {
                String IS_NO_SN = "501401";
                String IS_NO_SN_DESC = "未完成扫码，无法入库";
                String OS_NO_SN = "501402";
                String OS_NO_SN_DESC = "未完成扫码，无法出库";
            }

            /*即时兑付*/
            interface CR {
                String CR_AMOUNT_OVER = "501501";
                String CR_AMOUNT_OVER_DESC = "提取金额超过可提取金额";
                String CR_AMOUNT_NOT_ENOUGH = "501502";
                String CR_AMOUNT_NOT_ENOUGH_DESC = "提取失败，请稍后再试";
            }

            //采购退货 16
            interface PT {
                //该采购订单存在未记账的采购退货单
                String HAS_NOT_COMPLETE = "501601";
                //退货金额和明细退货金额合计不等
                String NOT_EQUALS_AMOUNT = "501602";
                //采购订单已完成退货
                String COMPLETE = "501603";
            }

        }

        interface Pdm {//PDM 60

            interface Goods {//01
                String EXIST_PARTS_NO = "600101";
                String EXIST_PARTS_NO_DESC = "商品编码已存在";
                String EXIST_GOODS_DESC = "600102";
                String EXIST_GOODS_DESC_DESC = "商品描述已存在";
            }
        }

        interface Pay {//70

            interface CashDraw {//01
                //账户余额
                String CAN_DRAW_CASH_NOT_ENOUGH = "700101";
                String CAN_DRAW_CASH_NOT_ENOUGH_DESC = "余额不足";
                String IS_BALANCE_ZERO = "700102";
            }

            interface Mail {//02
                //邮箱验证码
                String MAIL_VERIFICATION_CODE_ERROR = "700201";
                String MAIL_VERIFICATION_CODE_ERROR_DESC = "邮箱验证码错误";
                String MAIL_VERIFY_INVALID = "700202";
            }

            interface Alipay {//03
                //支付宝
                String ALIPAY_SERVER_ERROR = "700301";
                String ALIPAY_SERVER_FAIL = "700302";
                String ALIPAY_SERVER_UNKNOWN_ERROR = "700303";
                String ALIPAY_VERIFICATION_REMARK_ERROR = "700304";
                String ALIPAY_VERIFICATION_REMARK_ERROR_DESC = "支付宝转账备注错误";
                String ALIPAY_NOT_BIND = "700305";
                String ALIPAY_ERROR_TIMES = "700306";
                String PAYER_BALANCE_NOT_ENOUGH = "700307";//付款方余额不足
            }

            interface Wxpay {//04
                //微信
                String WXPAY_NOT_BIND = "700401";
                String WXPAY_NOT_SUPPORT = "700402";
            }

        }

        interface Vat {//04
            //微信
            String PUT_NEXT_VOLUME = "800101";

            //没有可用发票
            String NO_VALID_VAT = "800102";

            String VAT_ISSUE_OVERFULFIL = "800103";
            String VAT_ISSUE_OVERFULFIL_DESC = "超过应开金额";

            String CUSTOMER_VAT_ISSUE_OVERFULFIL = "800104";
            String CUSTOMER_VAT_ISSUE_OVERFULFIL_DESC = "客户存在多开发票";
        }

    }

}
