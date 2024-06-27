package com.server.vnnews.service;

import com.server.vnnews.entity.Follow;
import com.server.vnnews.entity.composite.FollowId;
import com.server.vnnews.exception.AppRuntimeException;
import com.server.vnnews.exception.DatabaseException;
import com.server.vnnews.repository.FollowRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Transactional
    public Follow saveFollow(Long followedId, Long followerId, Date time) {
        try{
            FollowId followId = new FollowId(followedId, followerId);
            Follow follow = new Follow(followId, time);
            return followRepository.save(follow);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }

    @Transactional
    public void deleteFollow(Long followedId, Long followerId) {
        try{
            followRepository.deleteByIdFollowedIdAndIdFollowerId(followedId, followerId);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }
}
