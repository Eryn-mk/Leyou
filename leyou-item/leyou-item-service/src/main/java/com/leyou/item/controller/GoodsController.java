package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key) {
        return ResponseEntity.ok(goodsService.querySpuByPage(
                key, page, rows, saleable));
    }

    // CRUD operation
//    /**
//     * 新增商品
//     * @param spu
//     * @return
//     */
//    @PostMapping("goods")
//    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu) {
//            goodsService.saveGoods(spu);
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    /**
//     * 修改商品
//     * @param spu
//     * @return
//     */
//    @PutMapping("goods")
//    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu) {
//        goodsService.updateGoods(spu);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//
//    /**
//     * 删除商品
//     * @param ids
//     * @return
//     */
//    @DeleteMapping("goods/spu/{id}")
//    public ResponseEntity<Void> deleteGoods(@PathVariable("id") String ids){
//        String separator="-";
//        if (ids.contains(separator)){
//            String[] goodsId = ids.split(separator);
//            for (String id:goodsId){
//                this.goodsService.deleteGoods(Long.parseLong(id));
//            }
//        }
//        else {
//            this.goodsService.deleteGoods(Long.parseLong(ids));
//        }
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    /**
//     * 根据spu的id查询详情detail
//     * @param spuId
//     * @return
//     */
//    @GetMapping("/spu/detail/{id}")
//    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id") Long spuId){
//        return ResponseEntity.ok(goodsService.queryDetailById(spuId));
//
//    }
//
}

