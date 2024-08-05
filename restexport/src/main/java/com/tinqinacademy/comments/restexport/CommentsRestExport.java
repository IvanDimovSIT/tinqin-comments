package com.tinqinacademy.comments.restexport;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "hotel")
@Headers({"Content-Type: application/json"})
public interface CommentsRestExport {


}
