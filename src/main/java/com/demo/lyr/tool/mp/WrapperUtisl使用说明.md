


        LambdaQueryWrapper<WorkOrderEntity> woeWrapper = new LambdaQueryWrapper<>();
        woeWrapper.orderByDesc(WorkOrderEntity::getCreateDate);
        WrapperUtil.like(woeWrapper, WorkOrderEntity::getWorkOrderNumber, dto.getWorkOrderNumber());
        WrapperUtil.eq(woeWrapper, WorkOrderEntity::getState, dto.getState());
        
        
                
        
        