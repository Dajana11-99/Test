package com.example.demo.mapper;


import com.example.demo.dto.OwnersReportDto;
import com.example.demo.model.OwnersReport;

public class OwnersReportMapper {
    public OwnersReport dtoToOwnersReport(OwnersReportDto ownersReportDto){
        return new OwnersReport(ownersReportDto.getId(),ownersReportDto.isBadComment(),ownersReportDto.getComment(),ownersReportDto.getOwnersUsername(),ownersReportDto.getClientUsername(),ownersReportDto.isApproved());
    }
    public OwnersReportDto ownersReportToDto(OwnersReport ownersReport){
        return new OwnersReportDto(ownersReport.getId(),ownersReport.isBadComment(),ownersReport.getComment(),ownersReport.isApproved(),ownersReport.getOwnersUsername(),ownersReport.getClientUsername(),false);
    }
}
