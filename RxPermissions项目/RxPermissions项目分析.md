## RxPermissions

### 一 对外暴露的API

权限的请求都是通过调用RxPermissions暴露的方法, 暴露的方法分为两类, 一类返回值为Observable, 另一类返回值为ObservableTransformer

1. 返回值为Observable的方法

		/**
         * 返回请求的所有权限是否被准许, 当某个权限被拒绝, 则返回被拒绝.
		 */
		public Observable<Boolean> request(final String... permissions)

		/**
		 * 依次返回请求的所有权限的包装对象Permission, 通过其granted属性, 判断当前 * Permission对象对应的权限是否被准许
		 */
		public Observable<Permission> requestEach(final String... permissions)
		
		/**
		 * 返回所有请求权限的包装对象Permission, 通过其granted属性, 
		 * 判断是否有某个权限被运行, 如果有, 则返回被允许
		 */
		public Observable<Permission> requestEachCombined(final String... permissions)

2. 返回值为ObservableTransformer的方法

		/**
         * 返回请求的所有权限是否被准许, 当某个权限被拒绝, 则返回被拒绝.
		 */
		public <T> ObservableTransformer<T, Boolean> ensure(final String... permissions)

		/**
		 * 依次返回请求的所有权限的包装对象Permission, 通过其granted属性, 判断当前 * Permission对象对应的权限是否被准许
		 */
		public <T> ObservableTransformer<T, Permission> ensureEach(final String... permissions)

		/**
		 * 返回所有请求权限的包装对象Permission, 通过其granted属性, 
		 * 判断是否有某个权限被运行, 如果有, 则返回被允许
		 */
		public <T> ObservableTransformer<T, Permission> ensureEachCombined(final String... permissions)

### 二 使用方式

1. 返回值为Observable的方法
		
		// this: Activity实例
		RxPermissions rxPermissions = new RxPermissions(this);

		// request方法
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
        	@Override
            public void accept(Boolean aBoolean) throws Exception {
            	Toast.makeText(getApplicationContext(), "" + aBoolean, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "" + aBoolean);
            }
        });

		// requestEach方法
        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
        	@Override
        	public void accept(Permission permission) throws Exception {
        		Log.e(TAG, "" + permission.name + ", 是否允许: " + permission.granted);
        	}
        });

		// requestEachCombined方法
        rxPermissions.requestEachCombined(permissions).subscribe(new Consumer<Permission>() {
        	@Override
        	public void accept(Permission permission) throws Exception {
            	Log.e(TAG, "" + permission.name + ", 是否允许: " + permission.granted);
        	}
        });

2. 返回值为ObservableTransformer的方法

		// this: Activity实例
		RxPermissions rxPermissions = new RxPermissions(this);

		// ensure方法
        RxView.clicks(findViewById(R.id.bt_rx_permissions_ensure))
                .compose(rxPermissions.ensure(permissions))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Toast.makeText(getApplicationContext(), "" + aBoolean, Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "" + aBoolean);
                    }
                });

		// requestEach方法
        RxView.clicks(findViewById(R.id.bt_rx_permissions_ensure))
                .compose(rxPermissions.ensureEach(permissions))
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        Log.e(TAG, "请求权限: "+permission.name+", 是否准许: "+permission.granted);
                    }
                });

		// ensureEachCombined方法
        RxView.clicks(findViewById(R.id.bt_rx_permissions_ensure))
                .compose(rxPermissions.ensureEachCombined(permissions))
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        Log.e(TAG, "请求权限: "+permission.name+", 是否准许: "+permission.granted);
                    }
                });
	

	