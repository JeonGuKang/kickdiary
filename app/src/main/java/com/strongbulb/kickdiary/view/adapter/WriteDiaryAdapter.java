package com.strongbulb.kickdiary.view.adapter;

/**
 * Created by JeonGuKang on 2017. 8. 28..
 */

public class WriteDiaryAdapter {


//    implements ItemTouchHelperAdapter, View.OnClickListener {
//    private static final int TYPE_TEXT = 0;
//    private static final int TYPE_IMAGE = 1;
//
//    private WeakReference<RecyclerView> mRecyclerView;
//    private final LayoutInflater mInflater;
//    private ArrayList<PostWrite> arrayList = new ArrayList<>();
//
//  public MedalPickAnalysisWriteAdapter(WriteDiaryActivity activity) {
//      mInflater = LayoutInflater.from(activity);
//      arrayList.add(PostWrite.createWithText(""));
//  }
//
//  @Override
//  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//    if (mRecyclerView != null) mRecyclerView.clear();
//    mRecyclerView = new WeakReference<>(recyclerView);
//  }
//
//  @Override
//  public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
//    if (mRecyclerView != null) {
//      mRecyclerView.clear();
//      mRecyclerView = null;
//    }
//  }
//
//  @Override
//  public AnalysisWriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    final View view = mInflater.inflate(R.layout.item_medalpick_analysis_write, parent, false);
//    AnalysisWriteViewHolder holder = new AnalysisWriteViewHolder(view);
//    holder.itemView.setOnClickListener(this);
//    switch (viewType) {
//      case TYPE_TEXT: {
//        holder.ivImage.setVisibility(View.GONE);
//        holder.ivImage.setVisibility(View.GONE);
//        holder.etContent.setVisibility(View.VISIBLE);
//        holder.etContent.setOnKeyListener((v, keyCode, event) -> {
//          int index = arrayList.indexOf(holder.item);
//          if ((index == 0 && getItemCount() == 1) || index == -1) return false;
//          if (keyCode == KeyEvent.KEYCODE_DEL
//              && NSStringUtil.isEmpty(holder.etContent.getText().toString())) {
//            if (v.getTag() != null) {
//              v.setTag(null);
//              if (PostWrite.isText(arrayList.get(index))) {
//                NSUtilManager.hideInputMethod(v);
//                onItemDismiss(index);
//              }
//            } else {
//              v.setTag(true);
//            }
//          } else {
//            v.setTag(null);
//          }
//          return false;
//        });
//        holder.etContent.setOnFocusChangeListener((v, b) -> {
//          RecyclerView recyclerView = mRecyclerView.get();
//          if (!b
//              && !recyclerView.isComputingLayout()
//              && NSStringUtil.isEmpty(holder.etContent.getText().toString())) {
//            int index = arrayList.indexOf(holder.item);
//            if (arrayList.size() > 1 && index != -1 && PostWrite.isText(arrayList.get(index))) {
//              onItemDismiss(index);
//            }
//          }
//        });
//        return holder;
//      }
//      default: {
//        holder.etContent.setVisibility(View.GONE);
//        holder.ivImage.setVisibility(View.VISIBLE);
//        return holder;
//      }
//    }
//  }
//
//  @Override
//  public int getItemViewType(int position) {
//    return PostWrite.isImage(arrayList.get(position)) ? TYPE_IMAGE : TYPE_TEXT;
//  }
//
//  @Override
//  public void onBindViewHolder(AnalysisWriteViewHolder holder, int position) {
//    int type = getItemViewType(position);
//    holder.item = arrayList.get(position);
//    switch (type) {
//      case TYPE_TEXT: {
//        onBindText(holder, position);
//        break;
//      }
//      case TYPE_IMAGE: {
//        onBindImage(holder, position);
//        break;
//      }
//    }
//  }
//
//  @Override
//  public void onClick(View view) {
//    NSUtilManager.hideInputMethod(view);
//    PostWrite write = (PostWrite) view.getTag();
//    final int nextPosition = arrayList.indexOf(write) + 1;
//    if (nextPosition == arrayList.size()) {
//      if (PostWrite.isImage(write)) {
//        addOrFocusEditText(nextPosition, view, true);
//      }
//    } else {
//      if (PostWrite.isImage(write) && PostWrite.isImage(arrayList.get(nextPosition))) {
//        addOrFocusEditText(nextPosition, view, true);
//      } else {
//        addOrFocusEditText(nextPosition, view, false);
//      }
//    }
//  }
//
//  private void addOrFocusEditText(int position, View itemView, boolean isAdd) {
//    if (isAdd) {
//      arrayList.add(position, PostWrite.createWithText(""));
//      notifyItemInserted(position);
//    }
//    RecyclerView recyclerView = mRecyclerView.get();
//    recyclerView.scrollToPosition(position);
//    recyclerView.post(() -> {
//      if (recyclerView.isAttachedToWindow()
//          && !((MedalPickAnalysisWriteActivity) recyclerView.getContext()).isFinishing()) {
//        View nextItemView = null;
//        for (int i = 0; i < recyclerView.getChildCount(); i++) {
//          if (recyclerView.getChildAt(i).equals(itemView)) {
//            nextItemView = recyclerView.getChildAt(i + 1);
//          }
//        }
//        if (nextItemView != null) {
//          AnalysisWriteViewHolder holder = (AnalysisWriteViewHolder) recyclerView.getChildViewHolder(nextItemView);
//          onFocusEditText(holder.etContent);
//        }
//      }
//    });
//  }
//
//  @Override
//  public int getItemCount() {
//    return arrayList.size();
//  }
//
//  @Override
//  public void onItemDismiss(int position) {
//    arrayList.remove(position);
//    notifyItemRemoved(position);
//  }
//
//  @Override
//  public boolean onItemMove(int fromPosition, int toPosition) {
//    if (fromPosition < toPosition) {
//      for (int i = fromPosition; i < toPosition; i++) {
//        Collections.swap(arrayList, i, i + 1);
//      }
//    } else {
//      for (int i = fromPosition; i > toPosition; i--) {
//        Collections.swap(arrayList, i, i - 1);
//      }
//    }
//    notifyItemMoved(fromPosition, toPosition);
//    return true;
//  }
//
//  public class AnalysisWriteViewHolder extends RecyclerView.ViewHolder {
//    public ImageView ivImage;
//    public EditText etContent;
//    public PostWrite item;
//
//    public AnalysisWriteViewHolder(View itemView) {
//      super(itemView);
//      ivImage = (ImageView) itemView.findViewById(R.id.item_medalpick_analysis_write_iv_image);
//      etContent = (EditText) itemView.findViewById(R.id.item_medalpick_analysis_write_et_content);
//      etContent.addTextChangedListener(new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//          item.setText(etContent.getText().toString());
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//      });
//    }
//  }
//
//  public void addItem(PostWrite item) {
//    arrayList.add(item);
//    notifyDataSetChanged();
//  }
//
//  public void removeItem(PostWrite item) {
//    int position = arrayList.indexOf(item);
//    arrayList.remove(item);
//    // Position 구분 지은건 마지막 아이템 삭제 시 Footer 때문에 IndexOutOfBoundsException 이 남
//    if (position > 0) {
//      notifyItemRemoved(position);
//      notifyItemRangeChanged(position, arrayList.size());
//    } else {
//      notifyDataSetChanged();
//    }
//  }
//
//  private void onBindText(final AnalysisWriteViewHolder holder, int position) {
//    Context context = holder.itemView.getContext();
//    holder.etContent.setHint(position == 0 && getItemCount() == 1 ? context.getString(R.string
//        .content) : "");
//    holder.etContent.setText(holder.item.getText());
//    holder.itemView.setTag(holder.item);
//  }
//
//  private void onBindImage(final AnalysisWriteViewHolder holder, int position) {
//    Context context = holder.ivImage.getContext();
//    if (position == 0 && getItemCount() == 1) {
//      holder.etContent.setHint(context.getString(R.string.content));
//    } else {
//      holder.etContent.setHint("");
//    }
//    holder.itemView.setTag(holder.item);
//    String path = holder.item.getImageUri();
//    holder.ivImage.setClickable(false);
//    if (!path.startsWith("http://")) Glide.with(context)
//        .load(Uri.parse(path))
//        .asBitmap()
//        .fitCenter()
//        .error(R.drawable.profile_basic)
//        .into(new BitmapImageViewTarget(holder.ivImage) {
//          @Override
//          protected void setResource(Bitmap resource) {
//            holder.ivImage.setImageBitmap(resource);
//          }
//        });
//      /*holder.ivImage
//    .setImageURI
//    (Uri.parse
//    (path)
//    );*/
//    else Glide.with(context)
//        .load(path)
//        .asBitmap()
//        .fitCenter()
//        .error(R.drawable.profile_basic)
//        .into(new BitmapImageViewTarget(holder.ivImage) {
//          @Override
//          protected void setResource(Bitmap resource) {
//            holder.ivImage.setImageBitmap(resource);
//          }
//        });
//    removeEmptyTexts();
//  }
//
//  public void removeEmptyTexts() {
//    int size = arrayList.size();
//    SparseArray<PostWrite> removeItems = new SparseArray<>();
//    if (size > 0) {
//      for (int i = 0; i < size - 1; ++i) {
//        PostWrite item = arrayList.get(i);
//        if (PostWrite.isTextEmpty(item)) {
//          removeItems.put(i, item);
//        }
//      }
//      int lastIndex = size - 1;
//      if (PostWrite.isImage(arrayList.get(lastIndex))) {
//        arrayList.add(PostWrite.createWithText(""));
//        new Handler().post(new Runnable() {
//          @Override
//          public void run() {
//            notifyItemInserted(lastIndex);
//          }
//        });
//      }
//    }
//    size = removeItems.size();
//    for (int i = 0; i < size; ++i) {
//      int key = removeItems.keyAt(i);
//      PostWrite v = removeItems.get(key);
//      arrayList.remove(v);
//      new Handler().post(new Runnable() {
//        @Override
//        public void run() {
//          notifyItemRemoved(key);
//        }
//      });
//
//    }
//  }
//
//  private void onFocusEditText(EditText editText) {
//    try {
//      if (!editText.isFocused()) {
//        editText.requestFocus();
//        editText.setSelection(editText.getText().length());
//        InputMethodManager imm = (InputMethodManager) mInflater.getContext().getSystemService
//            (Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(editText, 0);
//        NSUtilManager.showInputMethod(editText);
//      }
//    } catch (Exception ignore) {
//    }
//  }
//
//  public int getTextEnteredItemCount() {
//    int count = 0;
//    for (PostWrite write : arrayList) {
//      if (PostWrite.isTextEntered(write)) count++;
//    }
//    return count;
//  }
//
//  public int getImageItemCount() {
//    int count = 0;
//    for (PostWrite write : arrayList) {
//      if (PostWrite.isImage(write)) count++;
//    }
//    return count;
//  }
//
//  public void setArrayList(ArrayList<PostWrite> arrayList) {
//    this.arrayList = arrayList;
//    notifyDataSetChanged();
//  }
//
//  public ArrayList<PostWrite> getArrayList() {
//    return arrayList;
//  }
}
