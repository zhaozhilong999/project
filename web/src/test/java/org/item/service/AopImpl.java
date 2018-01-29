package org.item.service;

import org.springframework.stereotype.Service;

@Service
public class AopImpl implements AopInterface {

	@Override
	public void add() {
		System.out.println("添加数据");
	}

	@Override
	public void update() {
		System.out.println("修改数据");
	}

}
